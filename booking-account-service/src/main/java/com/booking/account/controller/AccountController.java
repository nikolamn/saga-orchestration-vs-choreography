package com.booking.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.account.dto.AccountRegisterRequest;
import com.booking.account.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createAccount(@RequestBody @Valid AccountRegisterRequest dto) {
		service.saveAccount(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Account successfully created!");
	}
}
