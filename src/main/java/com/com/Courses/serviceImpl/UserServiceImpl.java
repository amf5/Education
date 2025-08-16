package com.com.Courses.serviceImpl;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.com.Courses.dto.ChangePassword;
import com.com.Courses.dto.RequestChangePassword;
import com.com.Courses.dto.RequestUpdateUser;
import com.com.Courses.dto.ResponseChangePassword;
import com.com.Courses.dto.ResponseUpdatedUser;
import com.com.Courses.exception.UserNotFoundException;
import com.com.Courses.model.CustomerUserDetails;
import com.com.Courses.model.User;
import com.com.Courses.repository.UserRepository;
import com.com.Courses.service.CacheServiceI;
import com.com.Courses.service.CloudnairyServiceI;
import com.com.Courses.service.EmailServiceI;
import com.com.Courses.service.UserServiceI;
import com.com.Courses.token.TokenUtil;
@Service
public class UserServiceImpl implements UserServiceI {
private  final UserRepository userRepository;

private  final CloudnairyServiceI cloudnairyService;

private final PasswordEncoder passwordEncoder;

private final TokenUtil tokenUtil;

private final CacheServiceI cacheService;

private final EmailServiceI emailService;

public UserServiceImpl(UserRepository userRepository,
		
		CloudnairyServiceI cloudnairyService,
		PasswordEncoder passwordEncoder,
		TokenUtil tokenUtil,
		CacheServiceI cacheService,
		EmailServiceI emailService) {
	this.cloudnairyService=cloudnairyService;
	this.userRepository=userRepository;
	this.passwordEncoder=passwordEncoder;
	this.tokenUtil=tokenUtil;
	this.cacheService=cacheService;
	this.emailService=emailService;
	
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
	public  ResponseEntity<String> uploadImageAndSaveAtDataBase(MultipartFile coverImage, Long userId) throws Exception {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
		if(user.getImage()!=null) {
			try {
			cloudnairyService.deleteImageByUrl(user.getImage());}
			catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		
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
	
	
	@Override
	public ResponseEntity<String> deleteImageProfile(Long id) throws Exception{
		User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
		if(user.getImage()!=null) {
		try {
		cloudnairyService.deleteImageByUrl(user.getImage());
		}catch (Exception e) {
			throw new Exception("Error at delete image ");
		}
		user.setImage(null);
		
		userRepository.save(user);
		return new ResponseEntity("delete successfull",HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity("you aready have not image profile",HttpStatus.ACCEPTED);
		
	}
	@Override
	public ResponseEntity<ResponseChangePassword> changePassword(RequestChangePassword request,Long userId) throws Exception {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
user.setToken(null);
String token=null;
		if(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
			CustomerUserDetails userDetails=new CustomerUserDetails(user);
			token=tokenUtil.generateToken(userDetails);
			user.setPassword(passwordEncoder.encode(request.getNewPassword()));
			
			user.setToken(token);
			}
		else {
			
			throw new Exception("error password!!");
		}
		userRepository.save(user);
		ResponseChangePassword changePassword=new ResponseChangePassword();
		changePassword.setId(user.getId());
		changePassword.setToken(token);
		
		
		return new  ResponseEntity(changePassword,HttpStatus.ACCEPTED);
	}
	@Override
	public ResponseEntity<String> sendLinkToCheckForgotPassword(String email) {
     User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
     emailService.sendVerificationPasswordLink(email);
     
		return ResponseEntity.ok("check your email now");
	}
	@Override
	public boolean checkCode(String sendCode,String email) {
		 User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
		 String code=cacheService.getVerificationCode(email);
		return code.equals(sendCode);
		
	}
	@Override
	public ResponseEntity<String> changePassword(ChangePassword changePassword) {
		 User user=userRepository.findByEmail(changePassword.getEmail()).orElseThrow(()->new UserNotFoundException("User not found"));
		user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
		user.setToken(null);
		userRepository.save(user);
		return new ResponseEntity<String>("GO To LOGIN NOW By New Password ",HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
