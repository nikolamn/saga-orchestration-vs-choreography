package com.saga.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthRequest {

	@NotBlank(message = "Username cannot be empty")
	@Size(min = 12, max = 20, message = "Username must contain between 12 to 20 characters")
	private final String username;

	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, max = 64, message = "Password must contain between 8 and 64 characters")
	private final String password;
}
