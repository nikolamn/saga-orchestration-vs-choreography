package com.booking.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.dto.request.UserRegistrationRequest;
import com.booking.auth.dto.response.UserAuthResponse;
import com.booking.auth.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final RegistrationService registrationService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserAuthResponse> signup(@RequestBody @Valid UserRegistrationRequest request) {
		
		String token =  registrationService.signup(request);

		return ResponseEntity.status(HttpStatus.CREATED)
	            .body(new UserAuthResponse(token));
	}
}
