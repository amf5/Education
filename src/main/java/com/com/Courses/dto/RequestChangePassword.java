package com.com.Courses.dto;

import com.com.Courses.annotation.Password;

public class RequestChangePassword {
	
private String currentPassword;

@Password
private String newPassword;


public String getCurrentPassword() {
	return currentPassword;
}


public void setCurrentPassword(String currentPassword) {
	this.currentPassword = currentPassword;
}


public String getNewPassword() {
	return newPassword;
}


public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}


public RequestChangePassword(String currentPassword, String newPassword) {
	super();
	this.currentPassword = currentPassword;
	this.newPassword = newPassword;
}


public RequestChangePassword() {
	super();
}




}
