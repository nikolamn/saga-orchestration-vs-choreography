package com.booking.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRegisterRequest {

	@NotBlank(message = "Username cannot be empty")
	@Size(min = 12, max = 20, message = "Username must contain 12 to 20 characters")
	private String username;
	
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
	private String password;
	
	@NotBlank(message = "Role must be specified")
	private String role;
}
