package com.manage.employee.repository;

import com.manage.employeemanagementmodel.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
