package com.booking.accommodation.mapper;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.booking.accommodation.domain.Accommodation;
import com.booking.accommodation.domain.Address;
import com.booking.accommodation.dto.request.AccommodationDTO;


@Component
public class AccommodationMapper {

	public Accommodation toDomain(AccommodationDTO dto) {
		Address address = Address.builder()
				.country(dto.getAddress().getCountry())
				.city(dto.getAddress().getCity())
				.street(dto.getAddress().getStreet())
				.number(dto.getAddress().getNumber())
				.build();
		
		return Accommodation.builder()
				.name(dto.getName())
				.description(dto.getDescription())
				.address(address)
				.amenities(dto.getAmenities())
				.ownerId(dto.getOwnerId())
				// availb defualt
				.minNumberOfGuests(dto.getMinNumberOfGuests())
				.maxNumberOfGuests(dto.getMaxNumberOfGuests())
				.build();
	}
}