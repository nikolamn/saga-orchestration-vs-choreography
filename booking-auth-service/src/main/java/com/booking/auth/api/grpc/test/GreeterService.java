package com.booking.auth.api.grpc.test;

import com.booking.auth.grpc.test.GreeterGrpc;
import com.booking.auth.grpc.test.HelloRequest;
import com.booking.auth.grpc.test.HelloResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
		String name = request.getName();
		String message = "Hello, " + name + "!";
		HelloResponse response = HelloResponse.newBuilder().setMessage(message).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
