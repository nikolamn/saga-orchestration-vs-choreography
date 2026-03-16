package com.booking.account.repository.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.account.domain.Account;

@Repository
public interface AccountRepositoryJpa extends JpaRepository<Account, UUID>{
	
	boolean existsByUserId(UUID authUserId);
	
	boolean existsByEmail(String email);
}
