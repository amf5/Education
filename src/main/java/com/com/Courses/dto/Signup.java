package com.com.Courses.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.com.Courses.annotation.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class Signup {
@Password
private String password;
@NotNull
@NotBlank
private String name;
private String lastName;
@Email
private String email;


public Signup() {}




public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}



public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}




public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}




public Signup(String password, @NotNull @NotBlank String name, String lastName, @Email String email) {
	super();
	this.password = password;
	this.name = name;
	this.lastName = lastName;
	this.email = email;
}



}
