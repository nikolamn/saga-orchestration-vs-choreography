package com.saga.account.service;

import java.util.UUID;

import com.saga.account.dto.common.AccountDTO;

public interface AccountService {

	void save(AccountDTO dto);

	void requestDeletion(UUID userId);

	AccountDTO getCurrentUserAccount(UUID userId);

	void markDeleted(UUID userId);

	void markDeletionFailed(UUID userId);
}
