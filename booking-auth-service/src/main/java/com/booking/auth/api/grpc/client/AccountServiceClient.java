package com.booking.auth.api.grpc.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.auth.api.grpc.mapper.AccountMapper;
import com.booking.auth.api.rest.dto.AccountDTO;
import com.booking.auth.exception.DuplicateUserInfoException;
import com.booking.auth.grpc.AccountCreationRequest;
import com.booking.auth.grpc.AccountServiceGrpc;

import io.grpc.StatusRuntimeException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;

@RequiredArgsConstructor
@Component
public class AccountServiceClient {

	private final AccountMapper mapper;

	@GrpcClient("account-service")
	private AccountServiceGrpc.AccountServiceBlockingStub stub;

	public void createAccount(AccountDTO dto, UUID userId) {
		AccountCreationRequest grpcDto = mapper.toGrpcDTO(dto, userId);

		try {
			stub.createAccount(grpcDto);

		} catch (StatusRuntimeException e) {
			throw mapException(e);
		}
	}

	private RuntimeException mapException(StatusRuntimeException e) {

		return switch (e.getStatus().getCode()) {
			case ALREADY_EXISTS ->
				new DuplicateUserInfoException("???");
				
			case INVALID_ARGUMENT ->
				new ConstraintViolationException("INVALID FORMAT" + e.getMessage(), null);
				
			default -> 
				new RuntimeException();
		};
	}

}
