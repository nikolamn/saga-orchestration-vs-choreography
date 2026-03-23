package com.saga.auth.nats.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDeletionMessage {

	private String userId;
}
