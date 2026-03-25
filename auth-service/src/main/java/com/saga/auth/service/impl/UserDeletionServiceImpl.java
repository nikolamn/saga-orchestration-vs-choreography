package com.saga.auth.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.saga.auth.nats.messages.UserDeletionMessage;
import com.saga.auth.nats.publisher.PublishService;
import com.saga.auth.nats.subject.NatsSubjects;
import com.saga.auth.service.UserDeletionService;
import com.saga.auth.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDeletionServiceImpl implements UserDeletionService {

	private final UserService userService;
    private final PublishService publishService;

    @Override
    public void deleteUserCredentials(String userId) {
    	try {
    		userService.markDeleted(UUID.fromString(userId));
    		
    		publishService.publish(
    				NatsSubjects.USER_DELETION_COMPLETED,
    				new UserDeletionMessage(userId)
    			);
    		
    	} catch (Exception e) {

    		publishService.publish(
    				NatsSubjects.USER_DELETION_FAILED,
    				new UserDeletionMessage(userId)
    			);
    	}
    }
}
