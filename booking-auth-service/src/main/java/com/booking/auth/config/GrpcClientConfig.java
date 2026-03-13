package com.booking.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

//@Configuration
public class GrpcClientConfig {

//	@Value("${grpc.client.host}")
//    private String host;
//
//    @Value("${grpc.client.port}")
//    private int port;
//
//    @Bean
//    public ManagedChannel managedChannel() {
//        return ManagedChannelBuilder
//                .forAddress(host, port)
//                .usePlaintext() // dev
//                .build();
//    }
}