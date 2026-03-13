package com.booking.account.api.rest.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {

	@NotNull(message = "AuthUserId must be specified")
	private UUID authUserId;
	
	@NotBlank(message = "First name must be specified")
	@Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
	private String firstName;
	
	@NotBlank(message = "Last must be specified")
	@Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
	private String lastName;
	
    @NotBlank(message = "Gender must be specified")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
	private String gender;
	
    @NotBlank(message = "Birthdate must be specified")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birthdate must have format YYYY-MM-DD")
    private String birthDate;
	
    @NotBlank(message = "Email must be specified")
    @Email(message = "Invalid email format")
	private String email;
    
    @NotBlank(message = "Country must be specified")
    private String country;

    @NotBlank(message = "City must be specified")
    private String city;

    @NotBlank(message = "Street must be specified")
    private String street;

    @Positive(message = "Invalid street number")
    private int number;
}


