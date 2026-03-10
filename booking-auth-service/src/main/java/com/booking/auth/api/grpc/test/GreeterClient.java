package com.booking.auth.api.grpc.test;

import org.springframework.stereotype.Service;

import com.booking.auth.grpc.test.GreeterGrpc;
import com.booking.auth.grpc.test.HelloRequest;
import com.booking.auth.grpc.test.HelloResponse;

import net.devh.boot.grpc.client.inject.GrpcClient;


@Service
public class GreeterClient {

    // This name MUST match the name under grpc.client in your YAML
    @GrpcClient("greeter-service")
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    public String sayHello(String name) {
        try {
            HelloRequest request = HelloRequest.newBuilder().setName(name).build();
            HelloResponse response = blockingStub.sayHello(request);
            return response.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}