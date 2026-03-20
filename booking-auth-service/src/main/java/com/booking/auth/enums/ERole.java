package com.booking.auth.enums;

import com.booking.auth.exception.InvalidRoleException;

public enum ERole {

	UNAUTHENTICATED, HOST, GUEST;

	public static ERole toEnum(String role) {
		try {
			return ERole.valueOf(role.trim().toUpperCase());
		} catch (Exception e) {
			throw new InvalidRoleException("INVALID ROLE");
		}
	}

	public String authority() {
		return "ROLE_" + this.name();
	}
}
