package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Account;
import com.timesheet.dto.AccountRefreshTokenDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    @Query("SELECT acc FROM Account acc WHERE acc.refreshToken.token = ?1")
    Account findByRefreshToken(String refreshToken);
}
