package com.manage.employee.repository;

import com.manage.employee.entity.JobDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDepartmentRepository extends JpaRepository<JobDepartment, Integer> {
}
