package com.com.Courses.dto;

public class ErrorDto {
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ErrorDto(String error) {
		super();
		this.error = error;
	}

	public ErrorDto() {
		super();
	}
	
}
