package com.manage.employee.repository;

import com.manage.employeemanagementmodel.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
