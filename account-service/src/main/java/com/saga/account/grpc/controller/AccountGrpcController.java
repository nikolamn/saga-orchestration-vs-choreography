package com.saga.account.grpc.controller;

import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.AccountServiceGrpc;
import com.google.protobuf.Empty;
import com.saga.account.grpc.service.AccountGrpcService;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AccountGrpcController extends AccountServiceGrpc.AccountServiceImplBase {

	private final AccountGrpcService service;

	@Override
	public void createAccount(AccountCreationRequest request, StreamObserver<Empty> responseObserver) {

		service.registerAccount(request);

		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}
}