package com.booking.auth.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String gender;
	
    private String birthdate;
    
    private UpdateAddressDTO address;
}
