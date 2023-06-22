package com.manage.employee.repository;

import com.manage.employee.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
    @Query("SELECT checkIn FROM CheckIn checkIn WHERE MONTH(checkIn.checkInTime) = :monthNumber AND checkIn.employee.id = :employeeId")
    List<CheckIn> getListCheckinByEmployeeInParticularMonth(@Param("employeeId") int employeeId, @Param("monthNumber") int monthNumber);
}
