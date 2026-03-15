package com.booking.account.api.grpc.exception;

import com.booking.account.exception.DuplicateUserInfoException;

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
	public StatusRuntimeException handleValidation(ConstraintViolationException ex) {
		String msg = ex.getConstraintViolations().iterator().next().getMessage();
		return Status.INVALID_ARGUMENT
				.withDescription(msg)
				.asRuntimeException();
	}

	@GrpcExceptionHandler(Exception.class)
	public StatusRuntimeException handleGenericException(Exception ex) {
		return Status.INTERNAL
				.withDescription("INTERNAL SERVER ERROR")
				.asRuntimeException();
	}
}
