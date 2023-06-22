package com.manage.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manage.employee.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
