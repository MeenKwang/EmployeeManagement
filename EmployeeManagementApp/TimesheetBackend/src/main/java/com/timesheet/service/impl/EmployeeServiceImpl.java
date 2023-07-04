package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.ProfileDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ProfileDto getEmployeeInfo(String accountUsername) {
        return employeeRepository.getEmployeeInfo(accountUsername);
    }

    @Override
    public Integer getEmployeeId(String username) {
        return employeeRepository.getEmployeeId(username);
    }
}
