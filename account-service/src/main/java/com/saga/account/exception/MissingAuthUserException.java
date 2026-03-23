package com.saga.account.exception;

public class MissingAuthUserException extends RuntimeException{

		public MissingAuthUserException(String message) {
			super(message);
		}
	}
