package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.exception.AbsenceRequestNotFoundException;
import com.timesheet.dto.AbsenceDto;

import java.util.List;

public interface AbsenceService {
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);
    Absence saveAbsenceRequest(AbsenceDto absenceDto);
    boolean deletePendingAbsenceRequest(Integer absenceRequest) throws AbsenceRequestNotFoundException;
}
