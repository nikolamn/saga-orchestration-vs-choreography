package com.booking.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.dto.request.UpdateUserRequest;
import com.booking.auth.dto.response.UpdateResponse;
import com.booking.auth.security.CustomUserDetails;
import com.booking.auth.service.UserUpdateOrchestrator;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserUpdateOrchestrator orchestrator;

	@PatchMapping("/update")
	public ResponseEntity<UpdateResponse> updateUserAccount(@AuthenticationPrincipal CustomUserDetails details, @RequestBody @Valid UpdateUserRequest request) {
		orchestrator.updateUserAccount(request.getUser(), request.getAccount(), details.getId());

		return ResponseEntity.status(HttpStatus.OK).body(new UpdateResponse());
	}
}
