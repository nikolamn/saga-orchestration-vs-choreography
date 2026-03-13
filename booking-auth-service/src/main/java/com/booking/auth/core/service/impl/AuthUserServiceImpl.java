package com.booking.auth.core.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.auth.api.rest.dto.AuthUserDTO;
import com.booking.auth.api.rest.mapper.AuthUserMapper;
import com.booking.auth.core.domain.AuthUser;
import com.booking.auth.core.repository.AuthUserRepository;
import com.booking.auth.core.service.AuthUserService;
import com.booking.auth.exception.DuplicateUserInfoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

	private final AuthUserRepository repository;
	private final AuthUserMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UUID saveAuthUser(AuthUserDTO dto) {
		if (repository.existsByUsername(dto.getUsername())) {
			throw new DuplicateUserInfoException("USERNAME TAKEN");
		}

		AuthUser authUser = mapper.toDomain(dto);
		authUser.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

		repository.save(authUser);
		
		return authUser.getId();
	}

	@Override
	public Optional<AuthUser> getAuthUserByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	@Override
	public void deleteAuthUser(UUID userId) {
		repository.deleteById(userId);
	}
}
