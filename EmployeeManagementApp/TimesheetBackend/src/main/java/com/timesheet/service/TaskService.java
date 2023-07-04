package com.timesheet.service;

import com.timesheet.dto.TaskSelectDto;

import java.util.List;

public interface TaskService {
    List<TaskSelectDto> listAllTaskForFormByProjectId(Integer projectId);
}
