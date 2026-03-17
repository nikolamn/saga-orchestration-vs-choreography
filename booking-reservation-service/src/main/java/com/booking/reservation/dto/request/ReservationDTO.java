package com.booking.reservation.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

	@NotNull(message = "Accommodation ID cannot be empty")
	private UUID accommodationId;
	
	@NotNull(message = "User ID cannot be empty")
	private UUID userId;
	
	@FutureOrPresent
	@NotNull(message = "Beginning date cannot be empty")
	private LocalDate beginning;
	
	@Future
	@NotNull(message = "Ending date cannot be empty")
	private LocalDate ending;
	
	@Positive
	@NotNull(message = "Number of guests cannot be empty")
	private Integer guests;
}
