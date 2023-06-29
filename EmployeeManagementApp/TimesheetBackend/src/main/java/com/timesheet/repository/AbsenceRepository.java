package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Absence;
import com.timesheet.dto.AbsenceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    @Query("SELECT new com.timesheet.dto.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.typeOff.id, absence.dateRequest, absence.timeOff) " +
            "FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.typeOff.absenceType.id = ?3")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);
}