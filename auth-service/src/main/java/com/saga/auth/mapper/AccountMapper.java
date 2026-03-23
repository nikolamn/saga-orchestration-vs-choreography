package com.saga.auth.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.grpc.stubs.AccountCreationRequest;
import com.saga.auth.dto.common.AccountDTO;

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
