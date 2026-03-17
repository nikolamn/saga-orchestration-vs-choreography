package com.booking.accommodation.dto.request;

import java.util.Set;
import java.util.UUID;

import com.booking.accommodation.enums.AmenityType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccommodationDTO {

	@NotBlank(message = "Name cannot be empty")
	@Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
	private String name;

	@NotBlank(message = "Description cannot be empty")
	@Size(min = 5, max = 100, message = "Description must be between 5 and 100 characters")
	private String description;

	@NotNull
	@Valid
	private AddressDTO address;

	private Set<AmenityType> amenities;

	@NotNull(message = "Owner ID cannot be empty")
	private UUID ownerId;

//	@Valid
//    private List<AvailabilityDTO> availability;

	@NotNull 
	@Positive(message = "Minimum number of guests is required")
    private Integer minNumberOfGuests;

	@NotNull
	@Positive(message = "Maximum number of guests is required")
	private Integer maxNumberOfGuests;

}
