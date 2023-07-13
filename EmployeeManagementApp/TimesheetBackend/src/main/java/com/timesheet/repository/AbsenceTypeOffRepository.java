package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.AbsenceTypeOff;
import com.timesheet.dto.AbsenceTypeOffSelectDto;
import com.timesheet.dto.AbsenceTypeSelectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceTypeOffRepository extends JpaRepository<AbsenceTypeOff, Integer> {
    @Query("SELECT absenceTypeOff FROM AbsenceTypeOff absenceTypeOff WHERE absenceTypeOff.absenceType.name = ?1")
    List<AbsenceTypeOffSelectDto> listAllAbsenceTypeOffByAbsenceTypeName(String absenceTypeName);
}
