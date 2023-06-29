package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.ProfileDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT new com.timesheet.dto.ProfileDto(CONCAT(em.firstName, ' ', em.lastName), em.account.username, em.birthDate, em.department.name, em.jobDepartment.name ) " +
            "FROM Employee em WHERE em.account.username = ?1")
    ProfileDto getEmployeeInfo(String accountUsername);
}
