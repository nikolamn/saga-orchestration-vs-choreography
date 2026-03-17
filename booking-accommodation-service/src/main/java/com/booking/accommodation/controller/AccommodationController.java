package com.booking.accommodation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.accommodation.dto.request.AccommodationDTO;
import com.booking.accommodation.service.AccommodationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("accommodation")
public class AccommodationController {

	private final AccommodationService service;
	
	@PostMapping("/new")
	public ResponseEntity<String> createAccommodation(@Valid @RequestBody AccommodationDTO request) {
		service.save(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Accommodation successfully created!");
	}
}
