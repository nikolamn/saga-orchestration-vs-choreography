package com.booking.reservation.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.booking.reservation.domain.Reservation;
import com.booking.reservation.dto.request.ReservationDTO;
import com.booking.reservation.enums.EStatus;
import com.booking.reservation.mapper.ReservationMapper;
import com.booking.reservation.repository.ReservationRepository;
import com.booking.reservation.service.ReservationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository repository;
	private final ReservationMapper mapper;
	
	@Override
	@Transactional
	public void save(ReservationDTO dto) {
		validateDateRange(dto.getBeginning(), dto.getEnding());
		
		Reservation reservation = mapper.toDomain(dto);
		reservation.setStatus(EStatus.PENDING);
		
		repository.save(reservation);
	}
	
	private void validateDateRange(LocalDate start, LocalDate end) {
		if (end.isBefore(start) || end.isEqual(start)) {
			throw new IllegalArgumentException("Ending date must be at least one day after beginning date");
		}
	}
}
