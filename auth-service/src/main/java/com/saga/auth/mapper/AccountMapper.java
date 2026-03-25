package com.saga.auth.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.saga.auth.dto.common.AccountDTO;
import com.saga.grpc.stubs.AccountCreationRequest;

@Component
public class AccountMapper {

	public AccountCreationRequest toGrpcDTO(AccountDTO dto, UUID userId) {
		return AccountCreationRequest.newBuilder()
				.setAuthUserId(userId.toString())
				.setFirstName(dto.getFirstName())
				.setLastName(dto.getLastName())
				.setEmail(dto.getEmail())
				.setBirthdate(dto.getBirthdate())
				.build();
	}
}
