package com.com.Courses.exception;

public class UserNotFoundException  extends RuntimeException  {
	public UserNotFoundException(String message) {
		super(message);
		
	}
	
public UserNotFoundException(String message,Throwable cause) {
		
		super(message,cause);
	}
	
public UserNotFoundException(Throwable cause) {
	
	super(cause);
}
public UserNotFoundException() {
	
	super();
}
public UserNotFoundException(String message,Throwable cause,boolean enableSuppresion,boolean writableStackTrace) {
	
	super(message,cause,enableSuppresion, writableStackTrace);
}
}
