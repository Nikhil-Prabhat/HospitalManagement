package com.securityservice.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -5173588370251773247L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
