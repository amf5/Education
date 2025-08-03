package com.com.Courses.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.com.Courses.dto.RequestUpdateUser;
import com.com.Courses.dto.ResponseUpdatedUser;
import com.com.Courses.service.UserServiceI;
import com.com.Courses.token.TokenUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
private final UserServiceI userService;
private final TokenUtil tokenUtil;

public UserController(UserServiceI userService,
		TokenUtil tokenUtil	) {
	this.userService=userService;
	this.tokenUtil=tokenUtil;
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































}
