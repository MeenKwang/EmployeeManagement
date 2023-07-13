package com.timesheet.service;

import com.timesheet.dto.ProfileDto;
import com.timesheet.dto.StaffViewDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    ProfileDto getEmployeeInfo(String accountUsername);
    Integer getEmployeeId(String username);
    Page<StaffViewDto> getStaffListByNativeQuery(Integer buddyId, Integer pageNumber,
                                                 Integer pageSize, String nameSearch, String sortField, String sortOrder);
}
