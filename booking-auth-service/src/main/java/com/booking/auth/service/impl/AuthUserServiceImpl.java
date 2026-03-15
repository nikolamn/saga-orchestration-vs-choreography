package com.booking.auth.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.auth.domain.AuthUser;
import com.booking.auth.dto.AuthUserDTO;
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
	@Transactional
	public UUID saveAuthUser(AuthUserDTO dto) {
		if (repository.existsByUsername(dto.getUsername())) {
			throw new DuplicateUserInfoException("Username already taken");
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
