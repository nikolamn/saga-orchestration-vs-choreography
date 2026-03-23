package com.saga.account.nats.subscriber;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class SubscribeService {

	private final Connection nc;
	private final ObjectMapper objectMapper;
	private Dispatcher dispatcher;
	
    @PostConstruct
    public void subscribe() {
        dispatcher = nc.createDispatcher(message -> { 
        	log.info("Dispatcher initialized");
        });
    }
	
	public <T> void subscribe(String subject, Class<T> clazz, Consumer<T> handler) {
		dispatcher.subscribe(subject, msg -> {
			try {
				String json = new String(msg.getData(), StandardCharsets.UTF_8);
				
				T payload = objectMapper.readValue(json, clazz);
				
				handler.accept(payload);
			} catch (Exception e) {
                log.error("Failed to process message on '{}'", e.getMessage());
			}
		});
		
        log.info("subscribed to subject: hello");
	}
}
