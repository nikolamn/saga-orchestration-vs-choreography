package com.booking.auth.core.service;

import java.util.Optional;
import java.util.UUID;

import com.booking.auth.api.rest.dto.AuthUserDTO;
import com.booking.auth.core.domain.AuthUser;

public interface AuthUserService {

	public UUID saveAuthUser(AuthUserDTO dto);
	
	public Optional<AuthUser> getAuthUserByUsername(String username);

	public void deleteAuthUser(UUID userId);
}
