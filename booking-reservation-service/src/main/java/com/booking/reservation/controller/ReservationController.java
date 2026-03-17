package com.booking.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.reservation.dto.request.ReservationDTO;
import com.booking.reservation.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("reservation")
public class ReservationController {

	private final ReservationService service;

	@PostMapping("/new")
	public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationDTO request) {
		service.save(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Reservation successfully created!");
	}
}
