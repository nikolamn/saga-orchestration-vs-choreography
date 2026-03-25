package com.saga.account.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saga.account.domain.Account;
import com.saga.account.dto.common.AccountDTO;
import com.saga.account.enums.EStatus;
import com.saga.account.exception.AccountNotFoundException;
import com.saga.account.exception.DuplicateUserInfoException;
import com.saga.account.mapper.AccountMapper;
import com.saga.account.repository.AccountRepository;
import com.saga.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;
	private final AccountMapper mapper;

	@Override
	public void save(AccountDTO dto) {
		if (repository.existsByUserId(dto.getUserId())) {
		    throw new DuplicateUserInfoException("User ID already taken");
		}

		if (repository.existsByEmail(dto.getEmail())) {
		    throw new DuplicateUserInfoException("Email already taken");
		}
		
		Account account = mapper.toDomain(dto);

		repository.save(account);
	}
	
	@Override
	public void requestDeletion(UUID userId) {
		Account account = repository.findByUserId(userId).orElseThrow(() -> new AccountNotFoundException("Account not found"));

		account.setStatus(EStatus.PENDING_DELETION);
		
		repository.save(account);
	}
	
	@Override
	public Account getByUserId(UUID userId) {
		Account account = repository.findByUserId(userId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
		
		return account;
	}
	
	@Override
	public AccountDTO getCurrentUserAccount(UUID userId) {
		Account account = getByUserId(userId);
		
		return mapper.toDTO(account);
	}

	@Override
	public void markDeleted(UUID userId) {
		Account account = repository.findByUserId(userId).orElseThrow(() -> new AccountNotFoundException("Account not found"));

		account.setStatus(EStatus.DELETED);
	}

	@Override
	public void markDeletionFailed(UUID userId) {
		Account account = repository.findByUserId(userId).orElseThrow(() -> new AccountNotFoundException("Account not found"));

		account.setStatus(EStatus.CREATED);
	}
}
