package com.booking.auth.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.auth.client.AccountServicePort;
import com.booking.auth.dto.common.UpdateAccountDTO;
import com.booking.auth.dto.common.UpdateUserDTO;
import com.booking.auth.service.UserService;
import com.booking.auth.service.UserUpdateOrchestrator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUpdateOrchestratorImpl implements UserUpdateOrchestrator {

	private final UserService userService;

	private final AccountServicePort accountServicePort;

	@Override
	@Transactional 
	public void updateUserAccount(UpdateUserDTO user, UpdateAccountDTO account, UUID userId) {
		userService.update(user, userId);

		accountServicePort.updateAccount(account, userId);
	}
}
