package com.booking.auth.mapper;

import org.springframework.stereotype.Component;

import com.booking.auth.domain.User;
import com.booking.auth.enums.ERole;

@Component
public class UserMapper {

	public User toDomain(String username, String hash, String role) {
		return User.builder()
				.username(username)
				.passwordHash(hash)
				.role(ERole.toEnum(role))
				.build();
	};
}
