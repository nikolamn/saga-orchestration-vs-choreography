package com.booking.auth.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.booking.auth.dto.RegistrationRequest;
import com.booking.auth.service.AccountServicePort;
import com.booking.auth.service.AuthService;
import com.booking.auth.service.AuthUserService;
import com.booking.auth.service.RegistrationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Orchestrator service
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final AuthUserService authUserService;
	private final AuthService authService;

	private final AccountServicePort accountServicePort;

	@Override
	@Transactional
	public String signup(RegistrationRequest request) {

		UUID userId = authUserService.saveAuthUser(request.getAuthUser());

		accountServicePort.createAccount(request.getAccount(), userId);

		return authService.authenticateAndGetToken(
				request.getAuthUser().getUsername(),
				request.getAuthUser().getPassword());
	}
}
