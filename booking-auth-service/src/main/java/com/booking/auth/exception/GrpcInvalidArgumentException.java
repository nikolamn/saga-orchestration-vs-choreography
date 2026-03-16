package com.booking.auth.exception;

public class GrpcInvalidArgumentException extends RuntimeException {

	public GrpcInvalidArgumentException(String message) {
		super(message);
	}
}
