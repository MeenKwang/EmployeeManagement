package com.timesheet.service.impl;

import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.repository.CheckInRepository;
import com.timesheet.service.CheckInService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;

    public CheckInServiceImpl(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    @Override
    public List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request) {
        return checkInRepository.getAllCheckInBySpecificMonthAndYear(request);
    }
}
