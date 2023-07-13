package com.timesheet.dto;

import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

import java.time.LocalDate;

public class AbsenceDto {
    private Integer id;
    private String reason;
    private Integer employeeId;
    private Integer absenceTypeId;
    private Integer absenceTypeOffId;
    private LocalDate dateRequest;
    private LocalDate dateSubmit;
    private TypeTimeOff typeTimeOff;
    private Double timeOff;

    public AbsenceDto() {
    }

    public AbsenceDto(Integer id, String reason, Integer employeeId, Integer absenceTypeId, Integer absenceTypeOffId, LocalDate dateRequest, LocalDate dateSubmit, TypeTimeOff typeTimeOff, Double timeOff) {
        this.id = id;
        this.reason = reason;
        this.employeeId = employeeId;
        this.absenceTypeId = absenceTypeId;
        this.absenceTypeOffId = absenceTypeOffId;
        this.dateRequest = dateRequest;
        this.dateSubmit = dateSubmit;
        this.typeTimeOff = typeTimeOff;
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

    public Double getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Double timeOff) {
        this.timeOff = timeOff;
    }

    public TypeTimeOff getTypeTimeOff() {
        return typeTimeOff;
    }

    public void setTypeTimeOff(TypeTimeOff typeTimeOff) {
        this.typeTimeOff = typeTimeOff;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }
}
