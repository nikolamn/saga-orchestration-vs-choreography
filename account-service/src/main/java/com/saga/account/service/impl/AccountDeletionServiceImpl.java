package com.saga.account.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.saga.account.nats.messages.UserDeletionMessage;
import com.saga.account.nats.publisher.PublishService;
import com.saga.account.nats.subject.NatsSubjects;
import com.saga.account.service.AccountDeletionService;
import com.saga.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountDeletionServiceImpl implements AccountDeletionService {
	
	private final PublishService publishService;
	private final AccountService accountService;
	
	@Override
	public void requestUserAccountDeletion(UUID userId) {
		accountService.requestDeletion(userId);
		
		UserDeletionMessage msg = new UserDeletionMessage(userId.toString());
		publishService.publish(
			NatsSubjects.USER_DELETION_REQUSET,
			msg
		);
	}

	@Override
	public void markAccountAsDeleted(UUID userId) {
		accountService.markDeleted(userId);
	}

	@Override
	public void markAccountDeletionFailed(UUID userId) {
		accountService.markDeletionFailed(userId);
	}
}
