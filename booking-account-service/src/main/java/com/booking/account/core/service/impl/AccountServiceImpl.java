package com.booking.account.core.service.impl;

import org.springframework.stereotype.Service;

import com.booking.account.api.rest.dto.AccountDTO;
import com.booking.account.api.rest.mapper.AccountMapper;
import com.booking.account.core.domain.Account;
import com.booking.account.core.repository.AccountRepository;
import com.booking.account.core.service.AccountService;
import com.booking.account.exception.DuplicateUserInfoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;
	private final AccountMapper mapper;

	@Override
	public void save(AccountDTO dto) {

		if (repository.existsByAuthUserId(dto.getAuthUserId())) {
		    throw new DuplicateUserInfoException("User ID already taken");
		}

		if (repository.existsByEmail(dto.getEmail())) {
		    throw new DuplicateUserInfoException("Email already taken");
		}
		
		Account account = mapper.toDomain(dto);

		repository.save(account);
	}
}
