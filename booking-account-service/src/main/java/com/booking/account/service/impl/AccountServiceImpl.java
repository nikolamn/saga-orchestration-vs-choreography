package com.booking.account.service.impl;

import org.springframework.stereotype.Service;

import com.booking.account.domain.Account;
import com.booking.account.dto.common.AccountDTO;
import com.booking.account.exception.DuplicateUserInfoException;
import com.booking.account.mapper.AccountMapper;
import com.booking.account.repository.AccountRepository;
import com.booking.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
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
}
