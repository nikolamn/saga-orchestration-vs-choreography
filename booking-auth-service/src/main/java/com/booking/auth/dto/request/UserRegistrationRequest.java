package com.booking.auth.dto.request;

import com.booking.auth.dto.common.AccountDTO;
import com.booking.auth.dto.common.UserDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegistrationRequest {

    @Valid
    @NotNull
	private final UserDTO authUser;
    
    @Valid
    @NotNull
	private final AccountDTO account;
}
