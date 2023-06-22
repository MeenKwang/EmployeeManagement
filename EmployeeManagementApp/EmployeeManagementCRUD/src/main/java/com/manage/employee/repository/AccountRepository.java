package com.manage.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manage.employeemanagementmodel.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	@Query("SELECT acc FROM Account acc WHERE acc.username = :username")
	Account getAccountByUsername(@Param("username") String username);

}
