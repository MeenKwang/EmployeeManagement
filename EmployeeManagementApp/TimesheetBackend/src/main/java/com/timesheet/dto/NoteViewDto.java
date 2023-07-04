package com.timesheet.dto;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;

import java.time.LocalDate;

public class NoteViewDto {
    private Integer id;
    private String projectName;
    private String taskName;
    private String noteDescription;
    private Integer workingTime;
    private LocalDate dateSubmit;
    private TimeSheetStatus status;

    public NoteViewDto() {
    }

    public NoteViewDto(Integer id, String projectName, String taskName, String noteDescription, Integer workingTime, LocalDate dateSubmit, TimeSheetStatus status) {
        this.id = id;
        this.projectName = projectName;
        this.taskName = taskName;
        this.noteDescription = noteDescription;
        this.workingTime = workingTime;
        this.dateSubmit = dateSubmit;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
