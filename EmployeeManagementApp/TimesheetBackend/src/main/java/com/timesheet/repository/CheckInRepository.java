package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    @Query("SELECT new com.timesheet.dto.CheckInDto(checkIn.id, checkIn.checkInTime, checkIn.checkOutTime, checkIn.employee.id) " +
            "FROM CheckIn checkIn WHERE MONTH(checkIn.checkInTime) = :#{#request.month} AND YEAR(checkIn.checkInTime) = :#{#request.year} AND checkIn.employee.id = :#{#request.employeeId}")
    List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request);
}
