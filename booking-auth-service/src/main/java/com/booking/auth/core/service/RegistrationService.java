package com.booking.auth.core.service;

import com.booking.auth.api.rest.dto.RegistrationRequest;

public interface RegistrationService {

	public String signup(RegistrationRequest request);
}
