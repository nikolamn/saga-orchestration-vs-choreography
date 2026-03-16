package com.booking.auth.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.booking.auth.dto.common.AccountDTO;
import com.booking.grpc.stubs.AccountCreationRequest;
import com.booking.grpc.stubs.Address;

@Component
public class AccountMapper {

	public AccountCreationRequest toGrpcDTO(AccountDTO dto, UUID userId) {
		Address address = Address.newBuilder()
				.setCountry(dto.getAddress().getCountry())
				.setCity(dto.getAddress().getCity())
				.setStreet(dto.getAddress().getStreet())
				.setNumber(dto.getAddress().getNumber())
				.build();
		
		return AccountCreationRequest.newBuilder()
				.setAuthUserId(userId.toString())
				.setFirstName(dto.getFirstName())
				.setLastName(dto.getLastName())
				.setEmail(dto.getEmail())
				.setGender(dto.getGender())
				.setBirthdate(dto.getBirthdate())
				.setAddress(address)
				.build();
	}
}
