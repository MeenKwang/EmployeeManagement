package com.manage.job.service.impl;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.manage.job.repository.CheckInRepository;
import com.manage.job.repository.EmployeeRepository;
import com.manage.job.service.CheckInService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckinServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final EmployeeRepository employeeRepository;

    public CheckinServiceImpl(CheckInRepository checkInRepository, EmployeeRepository employeeRepository) {
        this.checkInRepository = checkInRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void generateCheckInPerDay() {
        List<Integer> employeeIdList = employeeRepository.getAllEmployeeIdNotCheckIn(LocalDate.now());
        for(Integer id : employeeIdList) {
            CheckIn checkIn = new CheckIn();
            Employee employee = new Employee();
            employee.setId(id);
            checkIn.setEmployee(employee);
            checkIn.setStatus(CheckInStatus.APPROVED);
            checkInRepository.save(checkIn);
        }
    }
}
