package com.com.Courses.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.com.Courses.dto.ChangePassword;
import com.com.Courses.dto.RequestChangePassword;
import com.com.Courses.dto.RequestUpdateUser;
import com.com.Courses.dto.ResponseChangePassword;
import com.com.Courses.dto.ResponseUpdatedUser;


public interface UserServiceI {

	 ResponseEntity<String> Update (RequestUpdateUser request,Long id);
 
	 ResponseEntity<String> uploadImageAndSaveAtDataBase(MultipartFile coverImage,Long userId) throws Exception ;
	 ResponseEntity<ResponseUpdatedUser> getuserDetails(Long userId);
	 ResponseEntity<String> deleteImageProfile(Long id) throws Exception;
	 ResponseEntity<ResponseChangePassword>changePassword(RequestChangePassword request,Long userId) throws Exception;
	 ResponseEntity<String>sendLinkToCheckForgotPassword(String email);
	 boolean checkCode( String sendCode,String email);
	 ResponseEntity<String> changePassword(ChangePassword changePassword);
}
