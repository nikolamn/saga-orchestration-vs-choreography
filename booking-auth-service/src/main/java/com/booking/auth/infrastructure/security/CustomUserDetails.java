package com.booking.auth.infrastructure.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L; 
	
	private UUID id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
}