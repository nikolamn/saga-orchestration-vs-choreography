package com.booking.reservation.mapper;

import org.springframework.stereotype.Component;

import com.booking.reservation.domain.Reservation;
import com.booking.reservation.dto.request.ReservationDTO;

@Component
public class ReservationMapper {

	public Reservation toDomain(ReservationDTO dto) {
		return Reservation.builder()
				.accommodationId(dto.getAccommodationId())
				.userId(dto.getUserId())
				.beginning(dto.getBeginning())
				.ending(dto.getEnding())
				.guests(dto.getGuests())
				.build();
	}
}
