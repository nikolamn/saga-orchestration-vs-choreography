package com.saga.account.grpc.server.service;

import com.saga.grpc.stubs.AccountCreationRequest;

public interface AccountGrpcService {

	void registerAccount(AccountCreationRequest request);
}
