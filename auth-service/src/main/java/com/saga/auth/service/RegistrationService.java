package com.saga.auth.service;

import com.saga.auth.dto.common.AccountDTO;
import com.saga.auth.dto.common.UserDTO;

public interface RegistrationService {

	void register(UserDTO authUser, AccountDTO account);
}
