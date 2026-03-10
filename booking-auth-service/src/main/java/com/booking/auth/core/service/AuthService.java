package com.booking.auth.core.service;

public interface AuthService {

	public String authenticateAndGetToken(String username, String password);
}
