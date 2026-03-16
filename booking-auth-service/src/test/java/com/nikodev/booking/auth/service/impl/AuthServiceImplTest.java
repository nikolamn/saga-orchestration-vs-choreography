package com.nikodev.booking.auth.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import com.booking.auth.exception.JwtAuthenticationException;
import com.booking.auth.security.TokenProvider;
import com.booking.auth.service.impl.AuthServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private TokenProvider tokenProvider;

	@InjectMocks
	private AuthServiceImpl authService;

//	@Test
//	void shouldAuthenticateAndReturnToken() {
//		Authentication authentication = mock(Authentication.class);
//
//		when(authenticationManager.authenticate(any())).thenReturn(authentication);
//
//		when(tokenProvider.generate(authentication)).thenReturn("jwt-token");
//
//		String token = authService.authenticateAndGetToken("johnSnow1122", "snowspassword");
//
//		assertEquals("jwt-token", token);
//	}
//
//	@Test
//	void shouldThrowIfAuthenticationFails() {
//		when(authenticationManager.authenticate(any()))
//				.thenThrow(new JwtAuthenticationException("INVALID CREDENTIALs"));
//
//		assertThrows(JwtAuthenticationException.class, () -> authService.authenticateAndGetToken("john", "wrong"));
//		
//		verify(tokenProvider, never()).generate(any());
//	}
}
