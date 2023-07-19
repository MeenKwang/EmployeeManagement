package com.manage.job.repository;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.job.dto.CheckInDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    @Query("SELECT new com.manage.job.dto.CheckInDto(checkIn.id, checkIn.checkInTime, checkIn.checkOutTime, checkIn.status, checkIn.employee.id) " +
            "FROM CheckIn checkIn WHERE DATE(checkIn.checkInTime) = ?2 AND checkIn.employee.id = ?1 AND checkIn.status = com.manage.employeemanagementmodel.entity.enums.CheckInStatus.APPROVED")
    CheckInDto getCheckInOfEmployeePerDay(Integer employeeId, LocalDate now);
}
