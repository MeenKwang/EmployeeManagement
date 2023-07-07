package com.timesheet.service;

import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.request_body.CheckInRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInService {
    List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request);
    boolean saveCheckPointInDay(Integer employeeId, LocalDateTime checkPointTime);
}
