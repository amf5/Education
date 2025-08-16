package com.com.Courses.dto;

public class RequestForgotPassword {
private String email;

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public RequestForgotPassword(String email) {
	super();
	this.email = email;
}

public RequestForgotPassword() {
	super();
}

}
