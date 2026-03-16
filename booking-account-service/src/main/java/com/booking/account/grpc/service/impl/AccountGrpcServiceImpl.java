package com.booking.account.grpc.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.booking.account.dto.common.AccountDTO;
import com.booking.account.exception.ValidationException;
import com.booking.account.grpc.service.AccountGrpcService;
import com.booking.account.mapper.AccountMapper;
import com.booking.account.service.AccountService;
import com.booking.grpc.stubs.AccountCreationRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountGrpcServiceImpl implements AccountGrpcService {

	private final AccountService accountService;
	private final AccountMapper mapper;

	private final Validator validator;

	@Override
	public void registerAccount(AccountCreationRequest request) {

		AccountDTO dto = mapper.grpcToDTO(request);

		validateDto(dto);

		accountService.save(dto);
	}

	private void validateDto(AccountDTO dto) {
		Set<ConstraintViolation<AccountDTO>> violations = validator.validate(dto);

		if (!violations.isEmpty()) {
			Map<String, String> errorMap = new HashMap<>();

			for (ConstraintViolation<AccountDTO> violation : violations) {
				String propertyPath = violation.getPropertyPath().toString();

				errorMap.putIfAbsent(propertyPath, violation.getMessage());
			}
			
			throw new ValidationException(errorMap.values().iterator().next());
		}
	}
}
