package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.timesheet.dto.AbsenceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.typeOff.id, absence.dateRequest, absence.typeTimeOff,absence.timeOff) " +
            "FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.typeOff.absenceType.id = ?3")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.typeOff.id, absence.dateRequest, absence.typeTimeOff, absence.timeOff) FROM Absence absence WHERE absence.dateRequest = ?1")
    AbsenceDto getByDate(LocalDate date);
    @Query("UPDATE Absence absence SET absence.status = ?2 WHERE absence.id = ?1")
    @Modifying
    void updateAbsenceStatus(Integer absenceId, AbsenceStatus status);
}