package com.booking.auth.service;

import com.booking.auth.dto.common.AccountDTO;
import com.booking.auth.dto.common.UserDTO;

public interface RegistrationService {

	void register(UserDTO authUser, AccountDTO account);
}
