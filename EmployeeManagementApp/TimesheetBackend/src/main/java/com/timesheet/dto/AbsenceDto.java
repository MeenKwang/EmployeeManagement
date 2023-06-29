package com.timesheet.dto;

import java.time.LocalDate;

public class AbsenceDto {
    private Integer id;
    private String reason;
    private Integer employeeId;
    private Integer absenceTypeOffId;
    private LocalDate dateSubmit;
    private Integer timeOff;

    public AbsenceDto() {
    }

    public AbsenceDto(Integer id, String reason, Integer employeeId, Integer absenceTypeOffId, LocalDate dateSubmit, Integer timeOff) {
        this.id = id;
        this.reason = reason;
        this.employeeId = employeeId;
        this.absenceTypeOffId = absenceTypeOffId;
        this.dateSubmit = dateSubmit;
        this.timeOff = timeOff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAbsenceTypeOffId() {
        return absenceTypeOffId;
    }

    public void setAbsenceTypeOffId(Integer absenceTypeOffId) {
        this.absenceTypeOffId = absenceTypeOffId;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public Integer getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Integer timeOff) {
        this.timeOff = timeOff;
    }
}
