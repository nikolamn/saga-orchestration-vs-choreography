package com.saga.auth.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import com.saga.auth.domain.User;
import com.saga.auth.enums.EStatus;
import com.saga.auth.exception.InvalidCredentialsException;
import com.saga.auth.security.TokenProvider;
import com.saga.auth.service.AuthService;
import com.saga.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final UserService userService;

	@Override
	public String authenticate(String username, String password) {

		User user = userService.getByUsername(username).orElse(null);

	    if (Objects.isNull(user) || user.getStatus() != EStatus.CREATED) {
	        throw new InvalidCredentialsException("Invalid credentials");
	    }

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			return tokenProvider.generate(authentication);

		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException("Invalid credentials");
		}
	}
}
