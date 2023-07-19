package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.dto.AbsenceViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.absenceType.id, absence.typeOff.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff, absence.timeOff, absence.status) " +
            "FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.typeOff.absenceType.id = ?3")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.absenceType.id, absence.typeOff.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff, absence.timeOff, absence.status) FROM Absence absence WHERE absence.dateRequest = ?1")
    AbsenceDto getByDate(LocalDate date);
    @Query("UPDATE Absence absence SET absence.status = ?2 WHERE absence.id = ?1")
    @Modifying
    void updateAbsenceStatus(Integer absenceId, AbsenceStatus status);
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.absenceType.id, absence.typeOff.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff,absence.timeOff, absence.status) " +
            "FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.employee.id = ?3")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);
    @Query("SELECT absence.dateRequest FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.employee.id = ?3")
    List<LocalDate> ListAllDayAbsenceInParticularMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);
    @Query("SELECT new com.timesheet.dto.AbsenceViewDto(absence.id, absence.absenceType.name, absence.typeTimeOff, " +
            "CASE WHEN typeOff IS NULL THEN 'NONE' ELSE typeOff.name END, absence.timeOff, absence.reason, absence.dateRequest, absence.status) " +
            "FROM Absence absence LEFT JOIN absence.typeOff typeOff ON absence.typeOff.id = typeOff.id WHERE absence.dateRequest = ?1 " +
            "AND absence.employee.id = ?2")
    List<AbsenceViewDto> getAbsenceByDateAndEmployee(LocalDate date, Integer employeeId);
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.absenceType.id, absence.typeOff.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff,absence.timeOff, absence.status) " +
            "FROM Absence absence WHERE absence.id = ?1")
    AbsenceDto findFormById(Integer id);
    @Query("SELECT new com.timesheet.dto.AbsenceViewDto(absence.id, absence.absenceType.name, absence.typeTimeOff, " +
            "CASE WHEN typeOff IS NULL THEN 'NONE' ELSE typeOff.name END, absence.timeOff, absence.reason, absence.dateRequest, absence.status) " +
            "FROM Absence absence LEFT JOIN absence.typeOff typeOff ON absence.typeOff.id = typeOff.id WHERE MONTH(absence.dateSubmit) = ?2 " +
            "AND YEAR(absence.dateSubmit) = ?3 AND absence.employee.id = ?1")
    List<AbsenceViewDto> listAllAbsenceOfStaffInParticularMonth(int staffId, int month, int year);
    @Modifying
    @Query("UPDATE Absence absence SET absence.status = ?2 " +
            "WHERE absence.id = ?1")
    void updatePendingAbsenceStatus(int absenceId, AbsenceStatus status);
}