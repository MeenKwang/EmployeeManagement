package com.manage.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.manage.employee.dto.EmployeeFormDto;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;

public interface EmployeeService {
	
	public static final int EMPLOYEES_PER_PAGE = 5;
	
	List<Employee> findAll();
	EmployeeFormDto findEmployeeFormById(Integer id) throws EmployeeNotFoundException;
	Employee save(Employee employee);
	void detete(Integer employeeId) throws EmployeeNotFoundException;
	List<Employee> findByIdNot(Integer id);
	Page<Employee> listByPage(Integer pageNum, String sortField, String sortDir, String keyword);
	boolean checkEmailUnique(Integer id, String email);
	Employee getEmployeeById(Integer id);
}
