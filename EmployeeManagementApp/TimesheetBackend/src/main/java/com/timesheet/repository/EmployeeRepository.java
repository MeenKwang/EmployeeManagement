package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.ProfileDto;
import com.timesheet.dto.StaffViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT new com.timesheet.dto.ProfileDto(CONCAT(em.firstName, ' ', em.lastName), em.account.username, em.birthDate, em.department.name, em.jobDepartment.name ) " +
            "FROM Employee em WHERE em.id = ?1")
    ProfileDto getEmployeeInfo(Integer id);
    @Query("SELECT em.id FROM Employee em WHERE em.account.username =?1")
    Integer getEmployeeId(String username);
    @Query(value = "SELECT employee.id AS id, CONCAT(employee.first_name, ' ', employee.last_name) AS fullName, jobDepartment.name AS jobDepartmentName, department.name AS departmentName, employee.photo " +
            "FROM Employee employee " +
            "JOIN job_department AS jobDepartment ON employee.job_department_id = jobDepartment.id " +
            "JOIN department AS department ON employee.department_id = department.id " +
            "WHERE employee.buddy_id = ?1", nativeQuery = true)
    Page<StaffViewDto> getAllStaffByBuddyId(Integer buddyId, Pageable pageable);

}
