package com.booking.auth.exception;

public class DuplicateUserInfoException extends RuntimeException{

	public DuplicateUserInfoException(String message) {
		super(message);
	}
}
