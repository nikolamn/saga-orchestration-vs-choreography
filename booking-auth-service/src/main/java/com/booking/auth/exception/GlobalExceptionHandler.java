package com.booking.auth.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.booking.auth.dto.response.ApiErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidArgument(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		return buildResponse(HttpStatus.BAD_REQUEST, "INVALID ARGUMENT: " + message);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidConstraint(ConstraintViolationException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, "INVALID FORMAT");
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		return buildResponse(HttpStatus.CONFLICT, "PERSISTENCE ERROR");
	}

	@ExceptionHandler(DuplicateUserInfoException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateUserInfo(DuplicateUserInfoException ex) {
		return buildResponse(HttpStatus.CONFLICT, "INVALID CREDENTIALS: " + ex.getMessage());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(EntityNotFoundException ex) {
		return buildResponse(HttpStatus.NOT_FOUND, "NOT FOUND: " + ex.getMessage());
	}

	@ExceptionHandler(JwtAuthenticationException.class)
	public ResponseEntity<ApiErrorResponse> handleJwtError(JwtAuthenticationException ex) {
		return buildResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED: " + ex.getMessage());
	}

	@ExceptionHandler(GrpcInvalidArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleGrpcInvalidArgument(GrpcInvalidArgumentException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, "INVALID FORMAT: " + ex.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, "INVALID REQUEST: Malformed JSON");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleInternalError(Exception ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
	}

//	@ExceptionHandler(UnauthorizedException.class)
//	public ResponseEntity<String> handleNotFound(UnauthorizedException ex) {
//		return ResponseEntity.badRequest().body(ex.getMessage());
//	}

//	@ExceptionHandler(ForbiddenException.class)
//	public ResponseEntity<String> handleForbidden(ForbiddenException ex) {
//		return ResponseEntity.badRequest().body(ex.getMessage());
//	} 

	// + jwt exs

	private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status, String message) {
		return ResponseEntity.status(status).body(new ApiErrorResponse(message));
	}
}
