package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.exception.AbsenceRequestNotFoundException;
import com.timesheet.dto.AbsenceDto;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceService {
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);
    Absence saveAbsenceRequest(AbsenceDto absenceDto);
    boolean deletePendingAbsenceRequest(Integer absenceRequest) throws AbsenceRequestNotFoundException;
    AbsenceDto getAbsenceByDate(LocalDate date);
    void updateAbsenceStatus(Integer absenceId, AbsenceStatus status);
}
