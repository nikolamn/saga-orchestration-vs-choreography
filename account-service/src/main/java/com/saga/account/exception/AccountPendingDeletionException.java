package com.saga.account.exception;

public class AccountPendingDeletionException extends RuntimeException{

	public AccountPendingDeletionException(String message) {
		super(message);
	}
}
