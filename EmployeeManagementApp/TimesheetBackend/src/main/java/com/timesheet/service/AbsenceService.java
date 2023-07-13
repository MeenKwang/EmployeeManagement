package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.exception.AbsenceRequestNotFoundException;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.dto.AbsenceViewDto;
import com.timesheet.dto.NoteViewDto;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceService {
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);
    Absence saveAbsenceRequest(AbsenceDto absenceDto);
    boolean deletePendingAbsenceRequest(Integer absenceRequest) throws AbsenceRequestNotFoundException;
    AbsenceDto getAbsenceByDate(LocalDate date);
    void updateAbsenceStatus(Integer absenceId, AbsenceStatus status);
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);
    List<LocalDate> ListAllDayAbsenceInParticularMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);
    List<AbsenceViewDto> getAbsenceByDateAndEmployee(LocalDate date, Integer employeeId);
    AbsenceDto findFormById(Integer id);
    List<AbsenceViewDto> listAllPendingAbsenceOfStaffInParticularMonthAndYear(int staffId, int month, int year);
    void updatePendingAbsenceStatus(int absenceId, AbsenceStatus status);
}
