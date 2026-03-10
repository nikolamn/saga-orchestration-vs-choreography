package com.booking.account.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.booking.account.domain.Account;
import com.booking.account.dto.AccountRegisterRequest;
import com.booking.account.enums.EGender;

@Component
public class AccountMapper {

	public Account toDomain(AccountRegisterRequest dto) {
		return Account.builder()
				.authUserId(dto.getAuthUserId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.gender(EGender.toEnum(dto.getGender()))
				.birthDate(dto.getBirthDate())
				.email(dto.getEmail())
				.build();
	};
}
