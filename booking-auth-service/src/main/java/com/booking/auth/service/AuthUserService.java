package com.booking.auth.service;

import java.util.Optional;

import com.booking.auth.domain.AuthUser;
import com.booking.auth.dto.AuthRegisterRequest;

public interface AuthUserService {

	public void saveAuthUser(AuthRegisterRequest dto);
	
	public Optional<AuthUser> getAuthUserByUsername(String username);
}
