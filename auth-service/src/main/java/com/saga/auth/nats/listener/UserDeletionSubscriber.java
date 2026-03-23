package com.saga.auth.nats.listener;

import org.springframework.stereotype.Component;

import com.saga.auth.nats.messages.UserDeletionMessage;
import com.saga.auth.nats.subject.NatsSubjects;
import com.saga.auth.nats.subscriber.SubscribeService;
import com.saga.auth.service.UserDeletionService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDeletionSubscriber {

	private final SubscribeService subscribeService;
	private final UserDeletionService userDeletionService;
	
	@PostConstruct
	public void register() {
		subscribeService.subscribe(
				NatsSubjects.USER_DELETION_REQUEST, 
				UserDeletionMessage.class, 
				this::handleDeletionRequest
			);
	}
	
	private void handleDeletionRequest(UserDeletionMessage message) {
		userDeletionService.deleteUserCredentials(message.getUserId());
	}
}
