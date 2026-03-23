package com.saga.auth.dto.response;

import lombok.Getter;

@Getter
public class AuthResponse {
	
	private String message = "Login successful";
	private String accessToken;
    private String tokenType = "Bearer";
    
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}