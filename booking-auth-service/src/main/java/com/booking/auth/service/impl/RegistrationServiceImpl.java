package com.booking.auth.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.booking.auth.client.AccountServicePort;
import com.booking.auth.dto.request.UserRegistrationRequest;
import com.booking.auth.service.AuthService;
import com.booking.auth.service.RegistrationService;
import com.booking.auth.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Orchestrator service
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final UserService userService;
	private final AuthService authService;

	private final AccountServicePort accountServicePort;

	@Override
	@Transactional
	public String signup(UserRegistrationRequest request) {

		UUID userId = userService.save(request.getAuthUser());

		accountServicePort.createAccount(request.getAccount(), userId);

		return authService.authenticateAndGetToken(request.getAuthUser().getUsername(), request.getAuthUser().getPassword());
	}
}
