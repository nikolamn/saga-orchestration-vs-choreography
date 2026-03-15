package com.booking.auth.dto.response;

import lombok.Getter;

@Getter
public class UserAuthResponse {
	
	private String accessToken;
    private String tokenType = "Bearer";
    
    public UserAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}