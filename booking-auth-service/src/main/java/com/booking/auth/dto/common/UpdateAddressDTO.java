package com.booking.auth.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressDTO {
    
	private String country;
	
    private String city;
	
	private String street;
	
	private String number;
}
