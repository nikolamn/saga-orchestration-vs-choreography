package com.booking.auth.service;

public interface AuthService {

	public String authenticateAndGetToken(String username, String password);
}
