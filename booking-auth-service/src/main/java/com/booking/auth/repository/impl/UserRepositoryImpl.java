package com.booking.auth.repository.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.auth.domain.User;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, UUID> {
	
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
}


