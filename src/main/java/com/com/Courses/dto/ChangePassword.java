package com.com.Courses.dto;

import com.com.Courses.annotation.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ChangePassword {
	@NotNull(message = "password mustn't null")
	@Password
	private String password;
	@NotNull(message = "email mustn't null")
	@Email(message = "Enter valid email")
	private String email;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ChangePassword () {}
	public ChangePassword(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
