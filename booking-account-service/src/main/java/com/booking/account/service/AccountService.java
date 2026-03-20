package com.booking.account.service;

import com.booking.account.dto.common.AccountDTO;
import com.booking.account.dto.common.UpdateAccountDTO;

public interface AccountService {

	public void save(AccountDTO dto);

	void update(UpdateAccountDTO dto);
}
