package com.booking.account.grpc.service;

import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.AccountUpdateRequest;

public interface AccountGrpcService {

	void registerAccount(AccountCreationRequest request);

	void update(AccountUpdateRequest request);
}
