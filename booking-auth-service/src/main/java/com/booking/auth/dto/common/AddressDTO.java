package com.booking.auth.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddressDTO {

	@NotBlank(message = "Country cannot be empty")
    private String country;
	
	@NotBlank(message = "City cannot be empty")
	private String city;
    
	@NotBlank(message = "Street cannot be empty")
	private String street;
    
	@NotBlank(message = "Street number cannot be empty")
	private String number;
}