package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Account;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.repository.AccountRepository;
import com.timesheet.service.AccountService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "employees", allEntries = true),
            @CacheEvict(value = "timesheets", allEntries = true)
    })
    public void logout() {

    }
}
