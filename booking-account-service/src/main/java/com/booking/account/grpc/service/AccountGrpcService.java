package com.booking.account.grpc.service;

import com.booking.grpc.stubs.AccountCreationRequest;

public interface AccountGrpcService {

	public void registerAccount(AccountCreationRequest request);
}
