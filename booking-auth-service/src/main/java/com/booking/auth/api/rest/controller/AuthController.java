package com.booking.auth.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.api.rest.dto.RegistrationRequest;
import com.booking.auth.core.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final RegistrationService registrationService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody @Valid RegistrationRequest request) {
		
		String token =  registrationService.signup(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(token);
	}
}
