package com.booking.auth.api.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccountDTO {

	// basic validation, Account Service will validate AccountDTO properly
	@NotBlank
    private String firstName;
	
	@NotBlank
    private String lastName;
	
	@NotBlank
    private String email;

	@NotBlank
    private String gender;

	@NotNull
    private String birthdate;
	
	@NotNull
    private AddressDTO address;
}