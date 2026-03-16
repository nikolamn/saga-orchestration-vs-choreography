package com.booking.account.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
