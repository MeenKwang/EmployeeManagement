package com.timesheet.service.impl;

import com.timesheet.dto.ProjectSelectDto;
import com.timesheet.repository.ProjectRepository;
import com.timesheet.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectSelectDto> getAllProjectsForForm() {
        return projectRepository.findAllProjectForForm();
    }
}
