package com.booking.auth.infrastructure.repository.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.auth.core.domain.AuthUser;

@Repository
public interface AuthUserRepositoryImpl extends JpaRepository<AuthUser, UUID> {
	
	Optional<AuthUser> findByUsername(String username);
	
	boolean existsByUsername(String username);
}


