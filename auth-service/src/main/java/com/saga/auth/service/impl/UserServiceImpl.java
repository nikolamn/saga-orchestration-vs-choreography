package com.saga.auth.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saga.auth.domain.User;
import com.saga.auth.dto.common.UserDTO;
import com.saga.auth.enums.EStatus;
import com.saga.auth.exception.DuplicateUserInfoException;
import com.saga.auth.exception.UserNotFoundException;
import com.saga.auth.mapper.UserMapper;
import com.saga.auth.repository.UserRepository;
import com.saga.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final UserMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UUID save(UserDTO dto) {
		checkUsernameAvailability(dto.getUsername());

		User user = mapper.toDomain(dto.getUsername(), encodePassword(dto.getPassword()));

		repository.save(user);

		return user.getId();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public void markDeleted(UUID userId) {
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		user.setStatus(EStatus.DELETED);
		
		repository.save(user); 
	}

	private void checkUsernameAvailability(String username) {
		if (repository.existsByUsername(username)) {
			throw new DuplicateUserInfoException("Username already taken");
		}
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	@Override
	public User getById(UUID userId) {
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		
		return user;
	}
}
