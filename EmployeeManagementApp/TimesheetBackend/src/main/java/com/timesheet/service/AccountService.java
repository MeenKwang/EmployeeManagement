package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Account;
import com.timesheet.configuration.security.CustomUserDetails;

public interface AccountService {
    Account save(Account account);
}
