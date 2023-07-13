package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.ProfileDto;
import com.timesheet.dto.StaffViewDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Page<StaffViewDto> getStaffListByNativeQuery(Integer buddyId, Integer pageNumber, Integer pageSize, String nameSearch, String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equals("ASC")) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return employeeRepository.getAllStaffByBuddyId(buddyId, pageable);
    }

}
