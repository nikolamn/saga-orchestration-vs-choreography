package com.saga.account.nats.publisher;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.nats.client.Connection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublishService {

	private final ObjectMapper objectMapper;
	private final Connection nc;
	

    public void publish(String subject, Object payload) {
    	try {
    		String json = objectMapper.writeValueAsString(payload);
    		
    		nc.publish(subject, json.getBytes(StandardCharsets.UTF_8));
    		
            log.info("Published message to {}: {}", subject, json);
    		
    	} catch (JsonProcessingException e) {
    		log.error("Failed to serialze payload");
    	}
    }
}