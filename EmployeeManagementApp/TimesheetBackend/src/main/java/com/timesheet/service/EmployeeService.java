package com.timesheet.service;

import com.timesheet.dto.ProfileDto;

public interface EmployeeService {
    ProfileDto getEmployeeInfo(String accountUsername);
}
