package com.booking.account.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRegisterRequest {

	@NotNull(message = "AuthUserId must be specified")
	private UUID authUserId;
	
	@NotBlank(message = "First name cannot be empty")
	@Size(min = 2, max = 20, message = "Username must contain 2 to 20 characters")
	private String firstName;
	
	@NotBlank(message = "Last name cannot be empty")
	@Size(min = 2, max = 20, message = "Username must contain 2 to 20 characters")
	private String lastName;
	
	@NotBlank(message = "Gender must be specified")
	private String gender;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date of birth must be specified")
	private LocalDate birthDate;
	
    @NotBlank
    @Email(message = "Email should be valid")
	private String email;
}
