package com.saga.auth.service;

import java.util.Optional;
import java.util.UUID;

import com.saga.auth.domain.User;
import com.saga.auth.dto.common.UserDTO;

public interface UserService {

	UUID save(UserDTO dto);
	
	User getById(UUID userId);
	
	Optional<User> getByUsername(String username);

	void markDeleted(UUID userId);
}
