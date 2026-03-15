package com.booking.account.core.application.impl;

import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.booking.account.api.rest.dto.AccountDTO;
import com.booking.account.api.rest.mapper.AccountMapper;
import com.booking.account.core.application.AccountApplicationService;
import com.booking.account.core.service.AccountService;
import com.booking.auth.grpc.AccountCreationRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountApplicationServiceImpl implements AccountApplicationService {

	private final AccountService accountService;
	private final AccountMapper mapper;

	private final Validator validator;
	
	@Override
	public void registerAccount(AccountCreationRequest request) {

		AccountDTO dto = mapper.grpcToDTO(request);

		validateAccountDTO(dto);

		accountService.save(dto);
	}

	private void validateAccountDTO(AccountDTO dto) {
		
		Set<ConstraintViolation<AccountDTO>> violations = validator.validate(dto);

		if (!violations.isEmpty()) {
			ConstraintViolation<AccountDTO> v = violations.iterator().next();
			
			System.out.println("MESSAGE " + v.getMessage());
			throw new ConstraintViolationException(v.getMessage(), Collections.singleton(v));
		}
	}
}
