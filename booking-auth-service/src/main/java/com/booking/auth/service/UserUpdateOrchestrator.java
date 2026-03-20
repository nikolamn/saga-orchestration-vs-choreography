package com.booking.auth.service;

import java.util.UUID;

import com.booking.auth.dto.common.UpdateAccountDTO;
import com.booking.auth.dto.common.UpdateUserDTO;

public interface UserUpdateOrchestrator {

	void updateUserAccount(UpdateUserDTO user, UpdateAccountDTO account, UUID userId);
}
