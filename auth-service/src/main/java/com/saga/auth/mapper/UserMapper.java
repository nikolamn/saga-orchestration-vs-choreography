package com.saga.auth.mapper;

import org.springframework.stereotype.Component;

import com.saga.auth.domain.User;
import com.saga.auth.enums.EStatus;

@Component
public class UserMapper {

	public User toDomain(String username, String hash) {
		return User.builder()
				.username(username)
				.passwordHash(hash)
				.status(EStatus.CREATED)
				.build();
	};
}
