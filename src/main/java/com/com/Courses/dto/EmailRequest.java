package com.com.Courses.dto;

import jakarta.validation.constraints.Email;

public class EmailRequest {
	@Email
private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmailRequest(@Email String email) {
		super();
		this.email = email;
	}

	public EmailRequest() {
		super();
	}
	
}
