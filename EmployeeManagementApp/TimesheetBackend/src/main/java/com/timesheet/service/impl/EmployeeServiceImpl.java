package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.ProfileDto;
import com.timesheet.dto.StaffViewDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.EmployeeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope("prototype")
//@Scope("singleton")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    static int a = 0;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        a++;
        System.out.println(a);
    }

    @Override
    @Cacheable(value = "employees", key = "#id")
    public ProfileDto getEmployeeInfo(Integer id) {
        return employeeRepository.getEmployeeInfo(id);
    }

    @Override
    public Integer getEmployeeId(String username) {
        return employeeRepository.getEmployeeId(username);
    }

    @Override
    public Page<StaffViewDto> getStaffListByNativeQuery(Integer buddyId, Integer pageNumber, Integer pageSize, String nameSearch, String sortField, String sortOrder) {
        Sort sort;
        //native query: we need to set the right field name in the database table
        if(sortField.equals("jobDepartment")) {
            sort = Sort.by("jobDepartment.name");
        } else if(sortField.equals("department")) {
            sort = Sort.by("department.name");
        } else if(sortField.equals("fullName")) {
            sort = Sort.by("first_name").and(Sort.by("last_name"));
        } else {
            sort = Sort.by("id");
        }

        if (sortOrder.equals("asc")) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return employeeRepository.getAllStaffByBuddyId(buddyId, pageable);
    }

}
