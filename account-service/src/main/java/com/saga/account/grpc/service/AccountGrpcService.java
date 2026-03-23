package com.saga.account.grpc.service;

import com.booking.grpc.stubs.AccountCreationRequest;

public interface AccountGrpcService {

	void registerAccount(AccountCreationRequest request);
}
