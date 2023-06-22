package com.manage.employee.dto;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;

import java.io.Serializable;
import java.time.LocalDate;

public class NoteFormDto implements Serializable {
    private Integer id;
    private String note;
    private LocalDate dateSubmit;
    private Integer workingTime;
    private Integer taskId;
    private WorkingType workingType;
    private TimeSheetStatus status;
    private Integer employeeId;

    public NoteFormDto() {
    }

    public NoteFormDto(Integer id, String note, LocalDate dateSubmit, Integer workingTime, Integer taskId, WorkingType workingType, TimeSheetStatus status, Integer employeeId) {
        this.id = id;
        this.note = note;
        this.dateSubmit = dateSubmit;
        this.workingTime = workingTime;
        this.taskId = taskId;
        this.workingType = workingType;
        this.status = status;
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    public TimeSheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSheetStatus status) {
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
