package com.booking.auth.service;

public interface AuthService {

	String authenticateAndGetToken(String username, String password);
}
