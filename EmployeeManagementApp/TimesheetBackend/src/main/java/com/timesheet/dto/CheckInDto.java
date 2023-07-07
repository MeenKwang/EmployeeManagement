package com.timesheet.dto;

import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;

import java.time.LocalDateTime;

public class CheckInDto {
    private Integer id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private CheckInStatus status;
    private Integer employeeId;

    public CheckInDto() {
    }

    public CheckInDto(Integer id, LocalDateTime checkInTime, LocalDateTime checkOutTime, CheckInStatus status, Integer employeeId) {
        this.id = id;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public CheckInStatus getStatus() {
        return status;
    }

    public void setStatus(CheckInStatus status) {
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
