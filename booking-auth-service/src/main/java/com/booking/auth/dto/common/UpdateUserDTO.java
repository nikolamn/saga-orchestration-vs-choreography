package com.booking.auth.dto.common;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {

	@Size(min = 12, max = 20, message = "Username must contain between 12 to 20 characters")
	private String username;

	@Size(min = 8, max = 64, message = "Password must contain between 8 and 64 characters")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number @.")
	private String password;
}
