package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Project;
import com.timesheet.dto.ProjectSelectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectSelectDto> getAllProjectsForForm();
}
