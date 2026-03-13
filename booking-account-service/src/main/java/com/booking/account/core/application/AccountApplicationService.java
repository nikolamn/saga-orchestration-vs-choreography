package com.booking.account.core.application;

import com.booking.auth.grpc.AccountCreationRequest;

public interface AccountApplicationService {

	public void registerAccount(AccountCreationRequest request);
}
