package com.com.Courses.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.com.Courses.dto.RequestUpdateUser;
import com.com.Courses.dto.ResponseUpdatedUser;


public interface UserServiceI {

	 ResponseEntity<String> Update (RequestUpdateUser request,Long id);
 
	 ResponseEntity<String> uploadImageAndSaveAtDataBase(MultipartFile coverImage,Long userId) ;
	 ResponseEntity<ResponseUpdatedUser> getuserDetails(Long userId);
}
