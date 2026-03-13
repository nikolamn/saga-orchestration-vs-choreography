package com.booking.account.api.rest.mapper;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.account.api.rest.dto.AccountDTO;
import com.booking.account.core.domain.Account;
import com.booking.account.core.domain.Address;
import com.booking.account.core.enums.EGender;
import com.booking.auth.grpc.AccountCreationRequest;

@Component
public class AccountMapper {

	public Account toDomain(AccountDTO dto) {
		Address address = Address.builder()
				.country(dto.getCountry())
				.city(dto.getCity())
				.street(dto.getStreet())
				.number(dto.getNumber())
				.build();

		return Account.builder()
				.authUserId(dto.getAuthUserId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.gender(EGender.toEnum(dto.getGender()))
				.birthdate(LocalDate.parse(dto.getBirthDate()))
				.email(dto.getEmail())
				.address(address)
				.build();
	}

	public AccountDTO grpcToDTO(AccountCreationRequest req) {
		return AccountDTO.builder()
				.authUserId(UUID.fromString(req.getAuthUserId()))
				.firstName(req.getFirstName())
				.lastName(req.getLastName())
				.gender(req.getGender())
				.birthDate(req.getBirthdate())
				.email(req.getEmail())
				.country(req.getAddress().getCountry())
				.city(req.getAddress().getCity())
				.street(req.getAddress().getStreet())
				.number(Integer.parseInt(req.getAddress().getNumber()))
				.build();
	};
}
