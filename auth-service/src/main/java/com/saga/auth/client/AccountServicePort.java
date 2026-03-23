package com.saga.auth.client;

import java.util.UUID;

import com.saga.auth.dto.common.AccountDTO;

public interface AccountServicePort {

	void createAccount(AccountDTO dto, UUID userId);
}
