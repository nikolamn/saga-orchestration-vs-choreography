package com.booking.account.api.grpc.controller;

import com.booking.account.core.application.AccountApplicationService;
import com.booking.auth.grpc.AccountCreationRequest;
import com.booking.auth.grpc.AccountServiceGrpc;
import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AccountGrpcController extends AccountServiceGrpc.AccountServiceImplBase {

	private final AccountApplicationService service;

	@Override
	public void createAccount(AccountCreationRequest request,
			StreamObserver<Empty> responseObserver) {

		service.registerAccount(request);

		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}
}