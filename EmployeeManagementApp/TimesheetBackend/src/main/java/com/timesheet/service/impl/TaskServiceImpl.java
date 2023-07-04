package com.timesheet.service.impl;

import com.timesheet.dto.TaskSelectDto;
import com.timesheet.repository.TaskRepository;
import com.timesheet.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskSelectDto> listAllTaskForFormByProjectId(Integer projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }
}
