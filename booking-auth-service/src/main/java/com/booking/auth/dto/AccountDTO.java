package com.booking.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotBlank(message = "Gender cannot be empty")
    private String gender;

	@NotBlank(message = "Birthday cannot be empty")
    private String birthdate;
	
	@NotNull(message = "Address cannot be empty")
    @Valid
    private AddressDTO address;
}