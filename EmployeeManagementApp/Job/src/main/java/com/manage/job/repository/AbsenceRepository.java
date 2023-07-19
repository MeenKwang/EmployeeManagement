package com.manage.job.repository;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.job.dto.AbsenceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    @Query("SELECT new com.manage.job.dto.AbsenceDto(absence.id, absence.absenceType.name, absence.typeOff.name, absence.dateRequest, absence.typeTimeOff, absence.timeOff) " +
            "FROM Absence absence WHERE absence.status = com.manage.employeemanagementmodel.entity.enums.AbsenceStatus.APPROVED " +
            "AND absence.employee.id = ?1 AND absence.dateRequest = ?2")
    List<AbsenceDto> getAbsenceInCurrentDay(Integer id, LocalDate now);
}
