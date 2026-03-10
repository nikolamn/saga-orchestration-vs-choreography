package com.booking.auth.core.service;

import java.util.Optional;

import com.booking.auth.api.rest.dto.AuthRegisterRequest;
import com.booking.auth.core.domain.AuthUser;

public interface AuthUserService {

	public void saveAuthUser(AuthRegisterRequest dto);
	
	public Optional<AuthUser> getAuthUserByUsername(String username);
}
