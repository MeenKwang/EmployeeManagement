package com.manage.job.repository;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.job.dto.EmployeeNotifyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT employee.id FROM Employee employee WHERE NOT EXISTS (SELECT c FROM CheckIn c where c.employee = employee AND DATE(c.checkInTime) = ?1)")
    List<Integer> getAllEmployeeIdNotCheckIn(LocalDate now);
    @Query("SELECT employee.id FROM Employee employee")
    List<Integer> findAllEmployeeId();
    @Query("SELECT new com.manage.job.dto.EmployeeNotifyDto(employee.id, employee.email, CONCAT(employee.firstName, ' ', employee.lastName)) FROM Employee employee")
    List<EmployeeNotifyDto> findAllToNotify();
}
