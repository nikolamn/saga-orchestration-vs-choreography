package com.booking.auth.service;

import java.util.Optional;
import java.util.UUID;

import com.booking.auth.domain.AuthUser;
import com.booking.auth.dto.AuthUserDTO;

public interface AuthUserService {

	public UUID saveAuthUser(AuthUserDTO dto);
	
	public Optional<AuthUser> getAuthUserByUsername(String username);

	public void deleteAuthUser(UUID userId);
}
