package com.saga.account.grpc.exception;

import com.saga.account.exception.AccountNotFoundException;
import com.saga.account.exception.DuplicateUserInfoException;
import com.saga.account.exception.ValidationException;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import jakarta.validation.ConstraintViolationException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcGlobalExceptionHandler {

	@GrpcExceptionHandler(DuplicateUserInfoException.class)
	public StatusRuntimeException handleDuplicateUser(DuplicateUserInfoException ex) {
		return Status.ALREADY_EXISTS
				.withDescription(ex.getMessage())
				.asRuntimeException();
	}
	
	@GrpcExceptionHandler(ConstraintViolationException.class)
	public StatusRuntimeException handleConstraintException(ConstraintViolationException ex) {
		String msg = ex.getConstraintViolations().iterator().next().getMessage();
		return Status.INVALID_ARGUMENT
				.withDescription("VALIDATION ERROR")
				.asRuntimeException();
	}
	
	@GrpcExceptionHandler(ValidationException.class)
	public StatusRuntimeException handleValidationException(ValidationException ex) {
		return Status.INVALID_ARGUMENT
				.withDescription(ex.getMessage())
				.asRuntimeException();
	}
	
	@GrpcExceptionHandler(AccountNotFoundException.class)
	public StatusRuntimeException handleNotFound(AccountNotFoundException ex) {
		return Status.NOT_FOUND
				.withDescription(ex.getMessage())
				.asRuntimeException();
	}

	@GrpcExceptionHandler(Exception.class)
	public StatusRuntimeException handleInternal(Exception ex) {
		return Status.INTERNAL
				.withDescription("INTERNAL SERVER ERROR")
				.asRuntimeException();
	}
}
