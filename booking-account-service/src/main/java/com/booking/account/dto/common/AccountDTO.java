package com.booking.account.dto.common;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

	@NotNull(message = "User ID cannot be empty")
	private UUID userId;
	
	@NotBlank(message = "First name cannot be empty")
	@Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
	private String firstName;
	
	@NotBlank(message = "Last name cannot be empty")
	@Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
	private String lastName;
	
    @NotBlank(message = "Gender cannot be empty")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
	private String gender;
	
    @NotBlank(message = "Birthdate must be specified")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birthdate must have format YYYY-MM-DD")
    private String birthdate;
    
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
	
    @NotNull
    @Valid
    private AddressDTO adress;
}


