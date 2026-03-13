package com.booking.auth.api.rest.mapper;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.booking.auth.api.rest.dto.AuthUserDTO;
import com.booking.auth.core.domain.AuthUser;
import com.booking.auth.core.enums.ERole;
import com.booking.auth.infrastructure.security.CustomUserDetails;

@Component
public class AuthUserMapper {

	public AuthUser toDomain(AuthUserDTO dto) {
		return AuthUser.builder()
				.username(dto.getUsername())
				.role(ERole.toEnum(dto.getRole()))
				.build();
	};

	public CustomUserDetails toCustomAuthUserDetails(AuthUser user, List<SimpleGrantedAuthority> authorities) {
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setId(user.getId());
		customUserDetails.setUsername(user.getUsername());
		customUserDetails.setPassword(user.getPasswordHash());
		customUserDetails.setAuthorities(authorities);
		return customUserDetails;
	}
}
