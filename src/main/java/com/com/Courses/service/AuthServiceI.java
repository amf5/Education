package com.com.Courses.service;

import org.springframework.http.ResponseEntity;

import com.com.Courses.dto.ActivateAccount;
import com.com.Courses.dto.ChangePassword;
import com.com.Courses.dto.Login;
import com.com.Courses.dto.Signup;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthServiceI {
	
	
public ResponseEntity<?> login( Login login) throws Exception ;
public ResponseEntity<?>signup(Signup signup) throws Exception;
	
public ResponseEntity<?> activateAccount(ActivateAccount account) throws Exception;


public ResponseEntity<?> resentCode(String email) throws MessagingException, Exception;



 public ResponseEntity<?> logout(HttpServletRequest request) ;


 
 
 public ResponseEntity<?> forgotMyPassword (String email) throws Exception;
 
 
 public ResponseEntity<?> changeMyPassword(ChangePassword changeMyPassword) throws Exception;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
