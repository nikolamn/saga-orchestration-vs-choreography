package com.booking.auth.client;

import java.util.UUID;

import com.booking.auth.dto.common.AccountDTO;

public interface AccountServicePort {

	void createAccount(AccountDTO dto, UUID userId);
}
