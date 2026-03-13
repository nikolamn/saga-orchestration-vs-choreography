package com.booking.auth.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddressDTO {

	@NotBlank
    private String country;
	
	@NotBlank
	private String city;
    
	@NotBlank
	private String street;
    
	@NotBlank
	private String number;
}