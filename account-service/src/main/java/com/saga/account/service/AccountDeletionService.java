package com.saga.account.service;

import java.util.UUID;

public interface AccountDeletionService {

	void requestUserAccountDeletion(UUID userId);
	
	void markAccountAsDeleted(UUID userId);

	void markAccountDeletionFailed(UUID userId);
}
