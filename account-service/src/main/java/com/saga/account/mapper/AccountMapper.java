package com.saga.account.mapper;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.grpc.stubs.AccountCreationRequest;
import com.saga.account.domain.Account;
import com.saga.account.dto.common.AccountDTO;
import com.saga.account.enums.EStatus;

@Component
public class AccountMapper {

	public Account toDomain(AccountDTO dto) {
		return Account.builder()
				.userId(dto.getUserId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.birthdate(LocalDate.parse(dto.getBirthdate()))
				.email(dto.getEmail())
				.status(EStatus.CREATED)
				.build();
	}

	public AccountDTO grpcToDTO(AccountCreationRequest req) {
		return AccountDTO.builder()
				.userId(UUID.fromString(req.getAuthUserId()))
				.firstName(req.getFirstName())
				.lastName(req.getLastName())
				.birthdate(req.getBirthdate())
				.email(req.getEmail())
				.build();
	};
	
	public AccountDTO toDTO(Account account) {
		return AccountDTO.builder()
				.userId(account.getUserId())
				.firstName(account.getFirstName())
				.lastName(account.getLastName())
				.birthdate(account.getBirthdate().toString())
				.email(account.getEmail())
				.build();
	}

}