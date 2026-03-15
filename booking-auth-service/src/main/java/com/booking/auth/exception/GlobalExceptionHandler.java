package com.booking.auth.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.booking.auth.dto.ApiError;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleInvalidArgument(MethodArgumentNotValidException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);

		String message = fieldError.getDefaultMessage();
		String response = "INVALID ARGUMENT: " + message;
		
		return ResponseEntity.badRequest().body(new ApiError(response));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleInvalidConstraint(ConstraintViolationException ex) {
		return ResponseEntity.badRequest().body("INVALID FORMAT");
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		return ResponseEntity.badRequest().body("PERSISTENCE ERROR");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleInternalError(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR");
	}

	@ExceptionHandler(DuplicateUserInfoException.class)
	public ResponseEntity<String> handleDuplicateUserInfo(Exception ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("INVALID CREDENTIALS: " + ex.getMessage());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(JwtAuthenticationException.class)
	public ResponseEntity<String> handleJwtError(JwtAuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}

	@ExceptionHandler(GrpcInvalidArgumentException.class)
	public ResponseEntity<String> handleGrpcInvalidArgument(GrpcInvalidArgumentException ex) {
		String body = "INVALID FORMAT: " + ex.getMessage();

		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body("INVALID REQUEST: Malformed JSON");
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
}
