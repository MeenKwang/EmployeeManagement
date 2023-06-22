package com.manage.employee.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.employee.dto.EmployeeFormDto;
import com.manage.employee.dto.mapper.employee.EmployeeFormMapper;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;
import com.manage.employee.repository.EmployeeRepository;
import com.manage.employee.service.EmployeeService;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeFormMapper employeeFormMapper;


	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeFormMapper employeeFormMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeFormMapper = employeeFormMapper;
		
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeFormDto findEmployeeFormById(Integer id) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id).get();
		EmployeeFormDto employeeFormDto = employeeFormMapper.employeeToEmployeeFormDto(employee);
		return employeeFormDto;
	}

	@Override
	@CachePut(value = "employeeCache", key = "#employee.id")
	public Employee save(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;
	}

	@Override
	@CacheEvict(value = "employeeCache", key = "#employeeId")
	public void detete(Integer employeeId) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).get();
		if(employee == null) {
			throw new EmployeeNotFoundException("Could not find any employee to delete");
		}
		// Remove the association with the buddy employee
	    if (employee.getBuddy() != null) {
	    	Set<Employee> employees =  getSubEmployee(employee.getBuddy().getId());
	    	System.out.println(employees);
	    	employees.remove(employee);
	        employee.setBuddy(null);
	    }
		for(Employee associatedEmployee : getSubEmployee(employeeId)) {
			associatedEmployee.setBuddy(null);
			employeeRepository.save(associatedEmployee);
		}
		employeeRepository.save(employee);
		
		employeeRepository.delete(employee);
	}
	
	private Set<Employee> getSubEmployee(Integer parentEmployeeId) {
		Set<Employee> subEmployees = employeeRepository.findSubEmployeeByParentEmployee(parentEmployeeId);
		return subEmployees;
	}

	@Override
	public List<Employee> findByIdNot(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepository.findByIdNot(id);
	}

	@Override
	public Page<Employee> listByPage(Integer pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		if(sortDir.equals("ASC")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		Pageable pageable = PageRequest.of(pageNum - 1, EMPLOYEES_PER_PAGE, sort);
		if(keyword != null) {
			return employeeRepository.findAll(keyword, pageable);
		} else {
			return employeeRepository.findAll(pageable);
		}
	}

	@Override
	public boolean checkEmailUnique(Integer id, String email) {
		Employee employee = employeeRepository.getEmployeeByEmail(email);
		if(employee == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if(isCreatingNew) {
			if(employee != null) return false;
		} else {
			if(employee.getId() != id) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	@Cacheable("employeeCache")
	public Employee getEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@CacheEvict("employeeCache")
	public void clearCacheById(int id) {
	}

	@CacheEvict(value = "employeeCache", allEntries = true)
	public void clearCache() {
	}
}
