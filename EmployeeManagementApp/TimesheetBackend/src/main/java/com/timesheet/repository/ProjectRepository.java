package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Project;
import com.timesheet.dto.ProjectSelectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT new com.timesheet.dto.ProjectSelectDto(project.id, project.name) FROM Project project")
    List<ProjectSelectDto> findAllProjectForForm();
}
