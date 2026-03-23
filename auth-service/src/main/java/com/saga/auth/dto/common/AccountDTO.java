package com.saga.auth.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountDTO {

	// basic validation, Account Service will validate AccountDTO properly
	@NotBlank(message = "First cannot be empty")
    private String firstName;
	
	@NotBlank(message = "Last cannot be empty")
    private String lastName;
	
	@NotBlank(message = "Email cannot be empty")
    private String email;

	@NotBlank(message = "Birthday cannot be empty")
    private String birthdate;
}