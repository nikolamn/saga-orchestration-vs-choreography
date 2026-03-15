package com.booking.auth.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.auth.domain.User;
import com.booking.auth.dto.common.UserDTO;
import com.booking.auth.exception.DuplicateUserInfoException;
import com.booking.auth.mapper.UserMapper;
import com.booking.auth.repository.UserRepository;
import com.booking.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final UserMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UUID save(UserDTO dto) {
		if (repository.existsByUsername(dto.getUsername())) {
			throw new DuplicateUserInfoException("Username already taken");
		}

		User user = mapper.toDomain(dto, passwordEncoder.encode(dto.getPassword()));

		repository.save(user);
		
		return user.getId();
	}

	@Override
	public Optional<User> getByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	@Override
	public void delete(UUID userId) {
		repository.deleteById(userId);
	}
}
