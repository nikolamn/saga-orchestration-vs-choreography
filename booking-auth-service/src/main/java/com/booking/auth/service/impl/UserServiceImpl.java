package com.booking.auth.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.booking.auth.domain.User;
import com.booking.auth.dto.common.UpdateUserDTO;
import com.booking.auth.dto.common.UserDTO;
import com.booking.auth.exception.DuplicateUserInfoException;
import com.booking.auth.exception.UserNotFoundException;
import com.booking.auth.mapper.UserMapper;
import com.booking.auth.repository.UserRepository;
import com.booking.auth.service.UserService;

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

		User user = mapper.toDomain(dto.getUsername(), encodePassword(dto.getPassword()), dto.getRole());

		repository.save(user);

		return user.getId();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public void delete(UUID userId) {
		repository.deleteById(userId);
	}

	@Override
	public void update(UpdateUserDTO dto, UUID userId) {
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		if (StringUtils.hasText(dto.getUsername()) && !dto.getUsername().equals(user.getUsername())) {
            checkUsernameAvailability(dto.getUsername());
            user.setUsername(dto.getUsername());
		}

		if (StringUtils.hasText(dto.getPassword())) {
            user.setPasswordHash(encodePassword(dto.getPassword()));
        }

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
}
