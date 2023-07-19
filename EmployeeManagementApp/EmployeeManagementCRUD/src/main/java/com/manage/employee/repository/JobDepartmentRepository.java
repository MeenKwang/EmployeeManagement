package com.manage.employee.repository;

import com.manage.employeemanagementmodel.entity.JobDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobDepartmentRepository extends JpaRepository<JobDepartment, Integer> {
    List<JobDepartment> findAll();
}
