package com.booking.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.booking.grpc.stubs.AccountServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Configuration
public class GrpcClientConfiguration {

	@Bean
    public AccountServiceGrpc.AccountServiceBlockingStub accountServiceStub(
            @GrpcClient("account-service") AccountServiceGrpc.AccountServiceBlockingStub stub) {
        return stub;
    }
}