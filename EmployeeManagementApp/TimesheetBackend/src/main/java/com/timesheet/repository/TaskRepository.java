package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Task;
import com.timesheet.dto.TaskSelectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT new com.timesheet.dto.TaskSelectDto(task.id, task.name) FROM Task task WHERE task.project.id = ?1")
    List<TaskSelectDto> findAllByProjectId(Integer projectId);
}
