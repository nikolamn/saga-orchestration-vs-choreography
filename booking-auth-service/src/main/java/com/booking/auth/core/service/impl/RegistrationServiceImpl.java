package com.booking.auth.core.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.booking.auth.api.grpc.client.AccountServiceClient;
import com.booking.auth.api.rest.dto.RegistrationRequest;
import com.booking.auth.core.service.AuthService;
import com.booking.auth.core.service.AuthUserService;
import com.booking.auth.core.service.RegistrationService;

import lombok.RequiredArgsConstructor;

// Orchestrator service
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final AuthUserService authUserService;
	private final AuthService authService;

	private final AccountServiceClient accountService;

	@Override
	public String signup(RegistrationRequest request) {
		
		UUID userId = authUserService.saveAuthUser(request.getAuthUser());

		try {
			accountService.createAccount(request.getAccount(), userId);
			
			System.out.println("ACCOUNT CREATED: " + userId);
			
			String token = authService.authenticateAndGetToken(request.getAuthUser().getUsername(), request.getAuthUser().getPassword());

			return token;
			
		} catch (Exception e) {
			authUserService.deleteAuthUser(userId);
			
			System.out.println("USER DELETED: " + userId);
			
			return "";
		}
	}
}
