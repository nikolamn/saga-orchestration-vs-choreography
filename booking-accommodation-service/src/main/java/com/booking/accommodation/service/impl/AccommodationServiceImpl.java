package com.booking.accommodation.service.impl;

import org.springframework.stereotype.Service;

import com.booking.accommodation.domain.Accommodation;
import com.booking.accommodation.dto.request.AccommodationDTO;
import com.booking.accommodation.mapper.AccommodationMapper;
import com.booking.accommodation.repository.AccommodationRepository;
import com.booking.accommodation.service.AccommodationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

	private final AccommodationRepository repository;
	private final AccommodationMapper mapper;
	
	@Override
	public void save(AccommodationDTO dto) {
		Accommodation entity = mapper.toDomain(dto);
		
		repository.save(entity);
	}
}
