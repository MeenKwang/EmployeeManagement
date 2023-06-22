package com.manage.employee.dto;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;

import java.time.LocalDate;

public class NoteDto {
    private Integer id;
    private String note;
    private LocalDate dateSubmit;
    private Integer workingTime;
    private String projectName;
    private String taskName;
    private WorkingType workingType;
    private TimeSheetStatus status;

    public NoteDto() {
    }

    public NoteDto(Integer id, String note, LocalDate dateSubmit, Integer workingTime, String projectName, String taskName, WorkingType workingType, TimeSheetStatus status) {
        this.id = id;
        this.note = note;
        this.dateSubmit = dateSubmit;
        this.workingTime = workingTime;
        this.projectName = projectName;
        this.taskName = taskName;
        this.workingType = workingType;
        this.status = status;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
}
