package com.manage.employee.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manage.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
	
	private final EmployeeService employeeService;
	
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("check_email_unique")
	public String checkEmailUnique(@RequestParam(name = "id", required = false) Integer id, @RequestParam("email") String email) {
		try {
			boolean isUnique = employeeService.checkEmailUnique(id, email);
			if(isUnique) return "Unique";
			else return "Duplicated";
		} catch (Exception e) {
			return "Something's Wrong";
		}
	}
}
