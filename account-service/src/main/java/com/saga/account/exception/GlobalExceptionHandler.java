package com.saga.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.saga.account.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountPendingDeletionException.class)
	public ResponseEntity<ApiErrorResponse> handleAccountPendingDeletion(AccountPendingDeletionException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiErrorResponse(ex.getMessage()));
	}
	
	@ExceptionHandler(AccountDeletedException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidArgument(AccountDeletedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiErrorResponse(ex.getMessage()));
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<String> handleInvalidArgument(MethodArgumentNotValidException ex) {
//		return ResponseEntity.badRequest().body("INVALID ARGUMENT");
//	}
//	
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<String> handleInvalidConstraint(ConstraintViolationException ex) {
//		return ResponseEntity.badRequest().body("INVALID FORMAT");
//	}
//	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//		return ResponseEntity.badRequest().body("PERSISTENCE ERROR");
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleInternalError(Exception ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR");
//	} 
//	
//	@ExceptionHandler(DuplicateUserInfoException.class)
//	public ResponseEntity<String> handleDuplicateUserInfo(Exception ex) {
//		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//	} 
}
