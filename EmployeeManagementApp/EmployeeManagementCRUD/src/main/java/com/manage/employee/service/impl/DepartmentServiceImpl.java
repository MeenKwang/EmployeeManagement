package com.manage.employee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manage.employeemanagementmodel.entity.Department;
import com.manage.employee.repository.DepartmentRepository;
import com.manage.employee.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

}
