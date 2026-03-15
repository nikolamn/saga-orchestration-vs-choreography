package com.booking.auth.service;

import java.util.UUID;

import com.booking.auth.dto.AccountDTO;

public interface AccountServicePort {

	public void createAccount(AccountDTO dto, UUID userId);
}
