package com.com.Courses.dto;


import com.com.Courses.annotation.Code;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;

public class ActivateAccount {
	@NotNull(message = "email mustn't null")
	@Email(message = "Enter valid email")
	private String email;
	@Code
	private String code;
	public ActivateAccount() {}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ActivateAccount(String email, String code) {
		super();
		this.email = email;
		this.code = code;
	}


}
