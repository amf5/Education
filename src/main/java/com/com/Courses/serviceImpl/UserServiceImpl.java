package com.com.Courses.serviceImpl;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.com.Courses.dto.RequestUpdateUser;
import com.com.Courses.dto.ResponseUpdatedUser;
import com.com.Courses.exception.UserNotFoundException;
import com.com.Courses.model.User;
import com.com.Courses.repository.UserRepository;
import com.com.Courses.service.CloudnairyServiceI;
import com.com.Courses.service.UserServiceI;
@Service
public class UserServiceImpl implements UserServiceI {
private  final UserRepository userRepository;

private  final CloudnairyServiceI cloudnairyService;

public UserServiceImpl(UserRepository userRepository,
		
		CloudnairyServiceI cloudnairyService) {
	this.cloudnairyService=cloudnairyService;
	this.userRepository=userRepository;
	
}
	@Override
	public ResponseEntity<String> Update(RequestUpdateUser request, Long id) {
	User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
	user.setFaceBook(request.getFaceBook());
	user.setGitHub(request.getGitHub());
	user.setLinkedIn(request.getLinkedIn());
	user.setYouTube(request.getYouTube());
	user.setName(request.getName());
	userRepository.save(user);
		return new ResponseEntity("successfull Updated",HttpStatus.ACCEPTED);
	}

	@Override
	public  ResponseEntity<String> uploadImageAndSaveAtDataBase(MultipartFile coverImage, Long userId) {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
		
		
	    try (InputStream inputStream = coverImage.getInputStream()) {
	        String imageUrl = cloudnairyService.uploadFileFromStream(inputStream);
	       user.setImage(imageUrl);
	       userRepository.save(user);
	       return new ResponseEntity(imageUrl,HttpStatus.ACCEPTED);	       
	    } catch (IOException e) {
	        throw new RuntimeException("Error while uploading cover image", e);
	    }


	}
	@Override
	public ResponseEntity<ResponseUpdatedUser> getuserDetails(Long userId) {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
		ResponseUpdatedUser userDetails=new ResponseUpdatedUser(user.getImage(),
				user.getName(),user.getYouTube()
				, user.getLinkedIn(), 
				user.getGitHub(), user.getFaceBook()); 
		return ResponseEntity.ok(userDetails);
	}

}
