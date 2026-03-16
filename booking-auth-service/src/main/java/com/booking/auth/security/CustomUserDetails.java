package com.booking.auth.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L; 
	
	private final UUID id;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
}