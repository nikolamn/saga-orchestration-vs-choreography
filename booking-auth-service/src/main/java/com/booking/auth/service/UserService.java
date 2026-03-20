package com.booking.auth.service;

import java.util.Optional;
import java.util.UUID;

import com.booking.auth.domain.User;
import com.booking.auth.dto.common.UpdateUserDTO;
import com.booking.auth.dto.common.UserDTO;

public interface UserService {

	UUID save(UserDTO dto);
	
	Optional<User> getByUsername(String username);

	void delete(UUID userId);

	void update(UpdateUserDTO dto, UUID userId);
}
