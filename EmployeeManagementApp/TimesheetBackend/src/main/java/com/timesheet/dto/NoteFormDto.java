package com.timesheet.dto;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;

import java.time.LocalDate;

public class NoteFormDto {
    private Integer id;
    private Integer employeeId;
    private Integer taskId;
    private String noteDescription;
    private Integer workingTime;
    private WorkingType workingType;
    private LocalDate dateSubmit;
    private TimeSheetStatus status;

    public NoteFormDto() {
    }

    public NoteFormDto(Integer id, Integer employeeId, Integer taskId, String noteDescription, Integer workingTime, WorkingType workingType, LocalDate dateSubmit, TimeSheetStatus status) {
        this.id = id;
        this.employeeId = employeeId;
        this.taskId = taskId;
        this.noteDescription = noteDescription;
        this.workingTime = workingTime;
        this.workingType = workingType;
        this.dateSubmit = dateSubmit;
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public TimeSheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSheetStatus status) {
        this.status = status;
    }
}
