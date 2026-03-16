package com.booking.account.mapper;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.account.domain.Account;
import com.booking.account.domain.Address;
import com.booking.account.dto.common.AccountDTO;
import com.booking.account.dto.common.AddressDTO;
import com.booking.account.enums.EGender;
import com.booking.grpc.stubs.AccountCreationRequest;

@Component
public class AccountMapper {

	public Account toDomain(AccountDTO dto) {
		Address address = Address.builder()
				.country(dto.getAdress().getCountry())
				.city(dto.getAdress().getCity())
				.street(dto.getAdress().getStreet())
				.number(dto.getAdress().getNumber())
				.build();

		return Account.builder()
				.userId(dto.getUserId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.gender(EGender.toEnum(dto.getGender()))
				.birthdate(LocalDate.parse(dto.getBirthdate()))
				.email(dto.getEmail())
				.address(address)
				.build();
	}

	public AccountDTO grpcToDTO(AccountCreationRequest req) {
		AddressDTO address = AddressDTO.builder()
				.country(req.getAddress().getCountry())
				.city(req.getAddress().getCity())
				.street(req.getAddress().getStreet())
				.number(Integer.parseInt(req.getAddress().getNumber()))
				.build();
		
		return AccountDTO.builder()
				.userId(UUID.fromString(req.getAuthUserId()))
				.firstName(req.getFirstName())
				.lastName(req.getLastName())
				.gender(req.getGender())
				.birthdate(req.getBirthdate())
				.email(req.getEmail())
				.adress(address)
				.build();
	};
}
