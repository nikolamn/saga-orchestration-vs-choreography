package com.saga.auth.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.saga.auth.dto.common.AccountDTO;
import com.saga.auth.dto.common.UserDTO;
import com.saga.auth.grpc.client.AccountServicePort;
import com.saga.auth.service.RegistrationService;
import com.saga.auth.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Orchestrator service
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final UserService userService;

	private final AccountServicePort accountServicePort;

	@Override
	@Transactional
	public void register(UserDTO authUser, AccountDTO account) {
		UUID userId = userService.save(authUser);

		accountServicePort.createAccount(account, userId);
	}
}
