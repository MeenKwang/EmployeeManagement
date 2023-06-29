package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.RefreshToken;
import com.timesheet.dto.AccountRefreshTokenDto;

public interface RefreshTokenService {
    Account findByToken(String refreshToken);
}
