package com.manage.project;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employee.entity.Project;
import com.manage.employee.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProjectRepositoryTests {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void createProject() {
        Project project = new Project();
//        project.setName("Training");
        project.setName("Support");
//        project.setName("Company Activities");

        Project savedProject = projectRepository.save(project);

        assertThat(savedProject.getId()).isGreaterThan(0);
    }
}
