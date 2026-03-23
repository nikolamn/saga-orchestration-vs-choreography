package com.booking.account.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.saga.account.mapper.AccountMapper;
import com.saga.account.repository.AccountRepository;
import com.saga.account.service.impl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@Mock
	private AccountRepository repository;

	@Mock
	private AccountMapper mapper;

	@InjectMocks
	private AccountServiceImpl accountService;

//	@Test
//	void shouldThrowWhenAuthUserIdExist() {
//		UUID uuid = UUID.fromString("1af776fd-4147-40b7-be74-fe83763d83e0");
//		AccountRegisterRequest dto = new AccountRegisterRequest(uuid, "Marko", "Markovic", "MALE",
//				LocalDate.parse("1990-09-09"), "marko@email.com");
//
//		when(repository.existsByAuthUserId(uuid)).thenReturn(true);
//
//		assertThrows(DuplicateUserInfoException.class, () -> accountService.saveAccount(dto));
//
//		verify(repository, never()).save(any());
//	}
//
//	@Test
//	void shouldSaveWhenUserInfoNotExist() {
//		UUID uuid = UUID.fromString("1af776fd-4147-40b7-be74-fe83763d83e0");
//		AccountRegisterRequest dto = new AccountRegisterRequest(uuid, "Marko", "Markovic", "MALE",
//				LocalDate.parse("1990-09-09"), "marko@email.com");
//
//		when(repository.existsByAuthUserId(uuid)).thenReturn(false);
//		when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
//
//		Account account = new Account();
//
//		when(mapper.toDomain(dto)).thenReturn(account);
//
//		accountService.saveAccount(dto);
//
//		verify(mapper).toDomain(dto);
//		verify(repository).save(account);
//	}
//
//	@Test
//	void shouldThrowWhenEmailExist() {
//		UUID uuid = UUID.fromString("1af776fd-4147-40b7-be74-fe83763d83e0");
//
//		AccountRegisterRequest dto = new AccountRegisterRequest(uuid, "Marko", "Markovic", "MALE",
//				LocalDate.parse("1990-09-09"), "marko@email.com");
//
//		when(repository.existsByAuthUserId(uuid)).thenReturn(false);
//		when(repository.existsByEmail(dto.getEmail())).thenReturn(true);
//
//		assertThrows(DuplicateUserInfoException.class, () -> accountService.saveAccount(dto));
//
//		verify(repository, never()).save(any());
//	}
}
