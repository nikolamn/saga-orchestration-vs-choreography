package com.booking.auth.client.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.auth.client.AccountServicePort;
import com.booking.auth.dto.common.AccountDTO;
import com.booking.auth.dto.common.UpdateAccountDTO;
import com.booking.auth.exception.DuplicateUserInfoException;
import com.booking.auth.exception.GrpcInvalidArgumentException;
import com.booking.auth.mapper.AccountMapper;
import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.AccountServiceGrpc;
import com.booking.grpc.stubs.AccountUpdateRequest;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountServiceAdapter implements AccountServicePort {

	private final AccountServiceGrpc.AccountServiceBlockingStub accountServiceStub;
	private final AccountMapper mapper;
	
	@Override
	public void createAccount(AccountDTO dto, UUID userId) {
		AccountCreationRequest grpcDto = mapper.toGrpcDTO(dto, userId);

		try {
			accountServiceStub.createAccount(grpcDto);

		} catch (StatusRuntimeException e) {
			throw mapException(e);
		}
	}
	
	@Override
	public void updateAccount(UpdateAccountDTO dto, UUID userId) {
		AccountUpdateRequest grpcUpdateDto = mapper.toGrpcUpdateDTO(dto, userId);
		
		try {
			accountServiceStub.updateAccount(grpcUpdateDto);
			
		} catch (StatusRuntimeException e) {
			throw mapException(e);
		}
	}

	private RuntimeException mapException(StatusRuntimeException e) {
		String cleanMessage = e.getStatus().getDescription();
		
		throw switch (e.getStatus().getCode()) {
			case ALREADY_EXISTS ->
				new DuplicateUserInfoException(cleanMessage != null ? cleanMessage : "CREDENTIALS TAKEN");
				
			case INVALID_ARGUMENT ->
				new GrpcInvalidArgumentException(cleanMessage != null ? cleanMessage : "INVALID FORMAT");
				
			default -> 
				new RuntimeException();
		};
	}

}
