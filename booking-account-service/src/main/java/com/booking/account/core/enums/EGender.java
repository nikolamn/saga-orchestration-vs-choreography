package com.booking.account.core.enums;

import com.booking.account.exception.InvalidGenderException;

public enum EGender {
	MALE, FEMALE, OTHER;
	
	public static EGender toEnum(String gender) {
		try {
			return EGender.valueOf(gender.trim().toUpperCase());
		} catch (Exception e) {
			throw new InvalidGenderException("INVALID GENDER");
		}
	}
}
