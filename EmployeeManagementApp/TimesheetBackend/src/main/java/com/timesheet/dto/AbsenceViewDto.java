package com.timesheet.dto;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

public class AbsenceViewDto {
    private Integer id;
    private String absenceType;
    private TypeTimeOff typeTimeOff;
    private String absenceTypeOff;
    private Double timeOff;
    private String reason;
    private AbsenceStatus status;

    public AbsenceViewDto() {
    }

    public AbsenceViewDto(Integer id, String absenceType, TypeTimeOff typeTimeOff, String absenceTypeOff, Double timeOff, String reason, AbsenceStatus status) {
        this.id = id;
        this.absenceType = absenceType;
        this.typeTimeOff = typeTimeOff;
        this.absenceTypeOff = absenceTypeOff;
        this.timeOff = timeOff;
        this.reason = reason;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(String absenceType) {
        this.absenceType = absenceType;
    }

    public TypeTimeOff getTypeTimeOff() {
        return typeTimeOff;
    }

    public void setTypeTimeOff(TypeTimeOff typeTimeOff) {
        this.typeTimeOff = typeTimeOff;
    }

    public String getAbsenceTypeOff() {
        return absenceTypeOff;
    }

    public void setAbsenceTypeOff(String absenceTypeOff) {
        this.absenceTypeOff = absenceTypeOff;
    }

    public Double getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Double timeOff) {
        this.timeOff = timeOff;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AbsenceStatus getStatus() {
        return status;
    }

    public void setStatus(AbsenceStatus status) {
        this.status = status;
    }
}
