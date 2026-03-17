package com.booking.accommodation.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "accommodation_availability")
public class Availability {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@NotNull
	@Column(name = "beginning")
	private LocalDate beginning;
	
	@NotNull
	@Column(name = "ending")
	private LocalDate ending;
	
	@Positive
	@Column(name = "price")
	private long price;
	
	@Column(name = "is_price_per_guest")
	private boolean isPricePerGuest;

	public Availability(LocalDate beginning, LocalDate ending, long price, boolean isPricePerGuest) {
		if (Objects.isNull(beginning) || Objects.isNull(ending)) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
		if (!ending.isAfter(beginning)) {
			throw new IllegalArgumentException("INVALID DATE");
		}
		this.beginning = beginning;
		this.ending = ending;
		this.price = price;
		this.isPricePerGuest = isPricePerGuest;
	}
}