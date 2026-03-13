package com.booking.auth.api.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegistrationRequest {

    @Valid
    @NotNull
	private AuthUserDTO authUser;
    
    @Valid
    @NotNull
	private AccountDTO account;
}
