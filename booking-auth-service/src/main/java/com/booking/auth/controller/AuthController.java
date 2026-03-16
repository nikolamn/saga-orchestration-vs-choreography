package com.booking.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.dto.request.AuthRequest;
import com.booking.auth.dto.request.SignupRequest;
import com.booking.auth.dto.response.AuthResponse;
import com.booking.auth.dto.response.SignupResponse;
import com.booking.auth.service.AuthService;
import com.booking.auth.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	// Orchestrator
	private final RegistrationService registrationService;
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest request) {
		registrationService.register(request.getAuthUser(), request.getAccount());

		return ResponseEntity.status(HttpStatus.CREATED).body(new SignupResponse());
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest request) {
		String token = authService.authenticate(request.getUsername(), request.getPassword());

		return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(token));
	}
}
