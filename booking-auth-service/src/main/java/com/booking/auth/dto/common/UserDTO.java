package com.booking.auth.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@NotBlank(message = "Username cannot be empty")
	@Size(min = 12, max = 20, message = "Username must contain between 12 to 20 characters")
	private String username;
	
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, max = 64, message = "Password must contain between 8 and 64 characters")
	private String password;
	
	@Pattern(regexp = "GUEST|HOST", message = "Role must be GUEST or HOST")
	@NotNull(message = "Role must be specified")
	private String role;
}
