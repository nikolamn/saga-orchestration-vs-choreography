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

		System.out.println("GRPC REQUEST ARRIVED: " + request.getEmail());

		service.registerAccount(request);

		Empty response = Empty.newBuilder().build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}