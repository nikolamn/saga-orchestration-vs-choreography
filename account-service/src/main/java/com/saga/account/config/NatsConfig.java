package com.saga.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.nats.client.Connection;
import io.nats.client.Nats;


@Configuration
public class NatsConfig {

	@Value("${nats.url}")
    private String NATS_URL;

    @Bean
    public Connection natsConnection() throws Exception {
        return Nats.connect(NATS_URL);
    }
}