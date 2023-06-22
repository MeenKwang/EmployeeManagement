package com.manage.task;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employee.entity.Project;
import com.manage.employee.entity.Task;
import com.manage.employee.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void createTaskInTrainingProject() {
        Task task = new Task();
        task.setProject(new Project(2, null));
//        task.setName("Coding");
//        task.setName("Unassigned");
//        task.setName("Training");
        task.setName("Candidate Interview");

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask.getId()).isGreaterThan(0);
    }

    @Test
    public void createTaskInSupportProject() {
        Task task = new Task();
        task.setProject(new Project(3, null));
        task.setName("Support");

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask.getId()).isGreaterThan(0);
    }

    @Test
    public void createTaskInCompanyActivitiesProject() {
        Task task = new Task();
        task.setProject(new Project(1, null));
//        task.setName("Unassigned");
        task.setName("Open Talk");

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask.getId()).isGreaterThan(0);
    }

}
