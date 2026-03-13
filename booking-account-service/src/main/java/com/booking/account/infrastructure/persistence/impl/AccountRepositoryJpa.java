package com.booking.account.infrastructure.persistence.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.account.core.domain.Account;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<Account, UUID>{
	
	boolean existsByAuthUserId(UUID authUserId);
	
	boolean existsByEmail(String email);
	
}
