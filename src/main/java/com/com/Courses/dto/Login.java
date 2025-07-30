package com.com.Courses.dto;

import com.com.Courses.annotation.Password;

import jakarta.validation.constraints.Email;


public class Login {
	@Email
	private String userName;
		
@Password
	private String password;
		
		
		public Login() {}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Login(String userName, String password) {
			super();
			this.userName = userName;
			this.password = password;
		}
		
		
		
}
