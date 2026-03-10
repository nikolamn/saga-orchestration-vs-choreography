package com.booking.auth.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.auth.domain.AuthUser;
import com.booking.auth.dto.AuthRegisterRequest;
import com.booking.auth.exception.DuplicateUserInfoException;
import com.booking.auth.mapper.AuthUserMapper;
import com.booking.auth.repository.AuthUserRepository;
import com.booking.auth.service.AuthUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

	private final AuthUserRepository repository;
	private final AuthUserMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void saveAuthUser(AuthRegisterRequest dto) {
		if (repository.existsByUsername(dto.getUsername())) {
			throw new DuplicateUserInfoException("USERNAME TAKEN");
		}

		AuthUser authUser = mapper.toDomain(dto);
		authUser.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

		repository.save(authUser);
	}

	@Override
	public Optional<AuthUser> getAuthUserByUsername(String username) {
		return repository.findByUsername(username);
	}
}
