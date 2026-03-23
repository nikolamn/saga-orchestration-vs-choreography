package com.saga.account.nats.listener;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.saga.account.nats.messages.UserDeletionMessage;
import com.saga.account.nats.subject.NatsSubjects;
import com.saga.account.nats.subscriber.SubscribeService;
import com.saga.account.service.AccountDeletionService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountDeletionSubscriber {

	private final SubscribeService subscribeService;
	private final AccountDeletionService accountDeletionService;
	
	@PostConstruct
	public void register() {
		subscribeService.subscribe(
				NatsSubjects.USER_DELETION_COMPLETED, 
				UserDeletionMessage.class, 
				this::handleDeletionCompleted
			);
		
		subscribeService.subscribe(
				NatsSubjects.USER_DELETION_FAILED, 
				UserDeletionMessage.class, 
				this::handleDeletionFailed
				);
	}
	
	private void handleDeletionCompleted(UserDeletionMessage message) {
		accountDeletionService.markAccountAsDeleted(UUID.fromString(message.getUserId()));
	}
	
	private void handleDeletionFailed(UserDeletionMessage message) {
		accountDeletionService.markAccountDeletionFailed(UUID.fromString(message.getUserId()));
	}
}
