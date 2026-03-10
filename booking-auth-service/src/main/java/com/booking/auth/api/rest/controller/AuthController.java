package com.booking.auth.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.api.rest.dto.AuthRegisterRequest;
import com.booking.auth.api.rest.dto.AuthRegisterResponse;
import com.booking.auth.core.service.AuthService;
import com.booking.auth.core.service.AuthUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthUserService authUserService;
	private final AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthRegisterResponse> signup(@RequestBody @Valid AuthRegisterRequest request) {
		
		authUserService.saveAuthUser(request); 
		
		String token = authService.authenticateAndGetToken(request.getUsername(), request.getPassword());

		return ResponseEntity.status(HttpStatus.CREATED).body(new AuthRegisterResponse(token));
	}
}
