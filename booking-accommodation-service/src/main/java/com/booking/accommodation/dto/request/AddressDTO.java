package com.booking.accommodation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	@NotBlank(message = "Country cannot be empty")
	private String country;

	@NotBlank(message = "City cannot be empty")
	private String city;

	@NotBlank(message = "Street cannot be empty")
	private String street;

	@Positive(message = "Invalid street number")
	private int number;
}
