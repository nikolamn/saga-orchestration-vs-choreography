package com.booking.auth.dto.request;

import com.booking.auth.dto.common.UpdateAccountDTO;
import com.booking.auth.dto.common.UpdateUserDTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

	@Valid
	private UpdateUserDTO user;
	
	private UpdateAccountDTO account;
}

