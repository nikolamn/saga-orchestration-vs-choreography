package com.booking.auth.client;

import java.util.UUID;

import com.booking.auth.dto.common.AccountDTO;
import com.booking.auth.dto.common.UpdateAccountDTO;

public interface AccountServicePort {

	void createAccount(AccountDTO dto, UUID userId);

	void updateAccount(UpdateAccountDTO dto, UUID userId);
}
