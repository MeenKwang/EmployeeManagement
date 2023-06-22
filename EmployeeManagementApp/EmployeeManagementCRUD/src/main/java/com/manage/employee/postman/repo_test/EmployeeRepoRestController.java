package com.manage.employee.postman.repo_test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manage.employee.entity.Employee;
import com.manage.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/postman")
public class EmployeeRepoRestController {
	
	private final EmployeeRepository employeeRepository;

	public EmployeeRepoRestController (EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping("employees_slice/{pageNum}")
	public ResponseEntity<?> listBySlice(@PathVariable("pageNum") Integer pageNum, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, @RequestParam("keyword") String keyword) {
		Sort sort = Sort.by(sortField);
		if (sortDir == "ASC")
			sort = sort.ascending();
		else
			sort = sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5);
		
		Slice<Employee> employeesSlice = employeeRepository.findAllWithSlice(keyword, pageable);
		
		return ResponseEntity.ok(employeesSlice);
	}
	
	@GetMapping("employees_page/{pageNum}")
	public ResponseEntity<?> listByPage(@PathVariable("pageNum") Integer pageNum, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, @RequestParam("keyword") String keyword) {
		Sort sort = Sort.by(sortField);
		if (sortDir == "ASC")
			sort = sort.ascending();
		else
			sort = sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5);
		
		Page<Employee> employeesSlice = employeeRepository.findAll(keyword, pageable);
		
		return ResponseEntity.ok(employeesSlice);
	}

	@GetMapping("employees/find_employee_profile/{employeeId}")
	public ResponseEntity<?> findEmployeeProfile(@PathVariable("employeeId") Integer employeeId) {
		return ResponseEntity.ok(employeeRepository.findEmployeeProfile(employeeId));
	}

	@GetMapping("employees/employee_basic_info_interface/{employeeId}")
	public ResponseEntity<?> getEmployeeBasicInfo(@PathVariable("employeeId") Integer employeeId) {
		return ResponseEntity.ok(employeeRepository.findEmployeeById(employeeId));
	}

	@GetMapping("employees/employee_basic_info_class/{employeeEmail}")
	public ResponseEntity<?> getEmployeeBasicInfo(@PathVariable("employeeEmail") String employeeEmail) {
		return ResponseEntity.ok(employeeRepository.findEmployeeByEmail(employeeEmail));
	}
}
