package com.saga.account.exception;

public class DuplicateUserInfoException extends RuntimeException{

	public DuplicateUserInfoException(String message) {
		super(message);
	}
}
