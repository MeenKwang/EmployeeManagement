package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.repository.AbsenceRepository;
import com.timesheet.repository.CheckInRepository;
import com.timesheet.service.CheckInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final AbsenceRepository absenceRepository;

    public CheckInServiceImpl(CheckInRepository checkInRepository, AbsenceRepository absenceRepository) {
        this.checkInRepository = checkInRepository;
        this.absenceRepository = absenceRepository;
    }

    @Override
    public List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request) {
        return checkInRepository.getAllCheckInBySpecificMonthAndYear(request);
    }

    @Override
    public boolean saveCheckPointInDay(Integer employeeId, LocalDateTime checkPointTime) {
        try {
            CheckInDto checkInDto = checkInRepository.getCheckInByLocalDateTime(checkPointTime);
            CheckIn checkIn = new CheckIn();
            Employee employee = new Employee();
            employee.setId(employeeId);
            checkIn.setEmployee(employee);
            if (checkInDto == null) {
                checkIn.setCheckInTime(checkPointTime);
                checkIn.setCheckOutTime(null);
                checkIn.setStatus(CheckInStatus.PENDING);
            } else {
                checkIn.setId(checkInDto.getId());
                checkIn.setCheckInTime(checkInDto.getCheckInTime());
                checkIn.setCheckOutTime(checkPointTime);
                checkIn.setStatus(checkInDto.getStatus());
            }
            checkInRepository.save(checkIn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
