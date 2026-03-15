package com.booking.auth.service;

import com.booking.auth.dto.request.UserRegistrationRequest;

public interface RegistrationService {

	public String signup(UserRegistrationRequest request);
}
