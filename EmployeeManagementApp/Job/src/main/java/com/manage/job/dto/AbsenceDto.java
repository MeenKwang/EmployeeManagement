package com.manage.job.dto;

import com.manage.employeemanagementmodel.entity.AbsenceType;
import com.manage.employeemanagementmodel.entity.AbsenceTypeOff;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

import java.time.LocalDate;

public class AbsenceDto {
    private Integer id;
    private String absenceTypeName;
    private String typeOff;
    private LocalDate dateRequest;
    private TypeTimeOff typeTimeOff;
    private Double timeOff;

    public AbsenceDto() {
    }

    public AbsenceDto(Integer id, String absenceTypeName, String typeOff, LocalDate dateRequest, TypeTimeOff typeTimeOff, Double timeOff) {
        this.id = id;
        this.absenceTypeName = absenceTypeName;
        this.typeOff = typeOff;
        this.dateRequest = dateRequest;
        this.typeTimeOff = typeTimeOff;
        this.timeOff = timeOff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
    }

    public String getTypeOff() {
        return typeOff;
    }

    public void setTypeOff(String typeOff) {
        this.typeOff = typeOff;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public TypeTimeOff getTypeTimeOff() {
        return typeTimeOff;
    }

    public void setTypeTimeOff(TypeTimeOff typeTimeOff) {
        this.typeTimeOff = typeTimeOff;
    }

    public Double getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Double timeOff) {
        this.timeOff = timeOff;
    }
}
