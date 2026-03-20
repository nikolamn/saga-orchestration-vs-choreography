package com.booking.account.grpc.controller;

import com.booking.account.grpc.service.AccountGrpcService;
import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.AccountServiceGrpc;
import com.booking.grpc.stubs.AccountUpdateRequest;
import com.google.protobuf.Empty;

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
	
	@Override
	public void updateAccount(AccountUpdateRequest request, StreamObserver<Empty> responseObserver) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		service.update(request);
		
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}
}