package com.saga.account.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.account.dto.common.AccountDTO;
import com.saga.account.dto.response.ApiResponse;
import com.saga.account.security.CustomUserDetails;
import com.saga.account.service.AccountDeletionService;
import com.saga.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountDeletionService deletationService;
	private final AccountService accountService;
	
	@GetMapping
	public ResponseEntity<AccountDTO> getMyAccount(@AuthenticationPrincipal CustomUserDetails userDetails) {
		UUID userId = userDetails.getId();
		
		AccountDTO dto = accountService.getCurrentUserAccount(userId);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(dto);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse> deleteUserAccount(@AuthenticationPrincipal CustomUserDetails userDetails) {
		UUID userId = userDetails.getId();
		
		deletationService.requestUserAccountDeletion(userId);
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(new ApiResponse("Account deletion pending."));
	}
}
