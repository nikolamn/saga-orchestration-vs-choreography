package com.saga.auth.repository.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saga.auth.domain.User;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, UUID> {
	
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
}


