package com.manage.employee.repository;

import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBonusRepository extends JpaRepository<EmployeeBonus, Integer> {
}
