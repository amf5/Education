package com.com.Courses.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
import com.com.Courses.service.UserServiceI;
import com.com.Courses.token.TokenUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
private final UserServiceI userService;
private final TokenUtil tokenUtil;
private final UserRepository userRepository;
private final CacheServiceI cacheService;

public UserController(UserServiceI userService,
		TokenUtil tokenUtil,
		 UserRepository userRepository,
		 CacheServiceI cacheService) {
	this.userService=userService;
	this.tokenUtil=tokenUtil;
	this.userRepository=userRepository;
	this.cacheService=cacheService;
	}


@PatchMapping("/update/{userId}")
public ResponseEntity<String> updateUserDetails(@RequestBody @Valid RequestUpdateUser request,
		@PathVariable Long userId,
		@RequestHeader("Authorization") String jwt) throws Exception{
	   Long compareUserId=tokenUtil.getIdFromBearerJwt(jwt);
	
	   if (!compareUserId.equals(userId) || userId == null) {
	        throw new Exception("Error....you don't have this id");
	    }
	   
return userService.Update(request, compareUserId);
	
}


@PatchMapping(value="/update/image/{userId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> updateImage(
	    @RequestPart("coverImage") MultipartFile coverImage,
		@PathVariable Long userId,
		@RequestHeader("Authorization") String jwt) throws Exception{
	   Long compareUserId=tokenUtil.getIdFromBearerJwt(jwt);
	
	   if (!compareUserId.equals(userId) || userId == null) {
	        throw new Exception("Error....you don't have this id");
	    }
	   
return userService.uploadImageAndSaveAtDataBase(coverImage, compareUserId);
	
}

@GetMapping(value="/{userId}")
public ResponseEntity<ResponseUpdatedUser> getDetailsUser(
	    
		@PathVariable Long userId,
		@RequestHeader("Authorization") String jwt) throws Exception{
	   Long compareUserId=tokenUtil.getIdFromBearerJwt(jwt);
	
	   if (!compareUserId.equals(userId) || userId == null) {
	        throw new Exception("Error....you don't have this id");
	    }
	   
return userService.getuserDetails(compareUserId);
	
}


@DeleteMapping("/delete-image/{userId}")
public ResponseEntity<String> deleteImage(@PathVariable Long userId
		,@RequestHeader("Authorization") String jwt) throws Exception{
	Long compareUserId=tokenUtil.getIdFromBearerJwt(jwt);
	
	   if (!compareUserId.equals(userId) || userId == null) {
	        throw new Exception("Error....you don't have this id");
	    }
	   

	
	return userService.deleteImageProfile(userId);
	
	
}

@PostMapping("/changePassword")
public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword){
	
	
	return userService.changePassword(changePassword);
	
}


@PostMapping("/changePassword/{userId}")
public ResponseEntity<ResponseChangePassword> changePassword(@RequestBody RequestChangePassword request,
		@PathVariable Long userId
		,@RequestHeader("Authorization") String jwt
		) throws Exception{
	Long compareUserId=tokenUtil.getIdFromBearerJwt(jwt);
	
	   if (!compareUserId.equals(userId) || userId == null) {
	        throw new Exception("Error....you don't have this id");
	    }
	return  userService.changePassword(request, compareUserId);
	}
@PostMapping("/send")
public ResponseEntity<String>sendLinkToChangePassword(@RequestParam String email){
	
	return userService.sendLinkToCheckForgotPassword(email);
	
	
	
}
@GetMapping("/code")
public ResponseEntity<?> verifyCodeAndServePage(
        @RequestParam String code,
        @RequestParam String email) {
	 User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
    boolean isValid = userService.checkCode(code, email);
    System.out.println(isValid);
if(isValid) {
	cacheService.removeVerificationCode(email);
	CustomerUserDetails userDetails=new CustomerUserDetails(user);
	String token=tokenUtil.generateTokenFive(userDetails);
	user.setToken(token);
	userRepository.save(user);
	URI redirectUri = URI.create("http://localhost:8081/reset-password.html?token=" + token + "&email=" + email);


    return ResponseEntity.status(HttpStatus.FOUND)
            .location(redirectUri)
            .build();
}

return ResponseEntity
        .badRequest()
        .body("Invalid code");

}









@GetMapping("/check")
public String  check(@RequestParam String email,@RequestParam String code) {
	return "hello";
	
}



















}
