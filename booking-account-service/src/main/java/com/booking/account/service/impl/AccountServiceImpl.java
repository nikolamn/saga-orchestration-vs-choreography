package com.booking.account.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.account.domain.Account;
import com.booking.account.dto.common.AccountDTO;
import com.booking.account.dto.common.UpdateAccountDTO;
import com.booking.account.exception.AccountNotFoundException;
import com.booking.account.exception.DuplicateUserInfoException;
import com.booking.account.mapper.AccountMapper;
import com.booking.account.repository.AccountRepository;
import com.booking.account.service.AccountService;

import lombok.RequiredArgsConstructor;

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
	public void update(UpdateAccountDTO dto) {
		Account account = repository.findByUserId(dto.getUserId())
	            .orElseThrow(() -> new AccountNotFoundException("Account not found: " + dto.getUserId()));
		
		
		Account acc = mapper.fromUpdateDTOtoDomain(dto, account);
		
		//  .save not necessary (dirty checking - managed by EntityManager)
		repository.save(acc);
	}
}
