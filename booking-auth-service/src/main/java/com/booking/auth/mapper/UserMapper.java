package com.booking.auth.mapper;

import org.springframework.stereotype.Component;

import com.booking.auth.domain.User;
import com.booking.auth.dto.common.UserDTO;
import com.booking.auth.enums.ERole;

@Component
public class UserMapper {

	public User toDomain(UserDTO dto, String hash) {
		return User.builder()
				.username(dto.getUsername())
				.passwordHash(hash)
				.role(ERole.toEnum(dto.getRole()))
				.build();
	};
}
