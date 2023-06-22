package com.manage.job_department;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employeemanagementmodel.entity.JobDepartment;
import com.manage.employee.repository.JobDepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class JobDepartmentRepositoryTests {

    @Autowired
    private JobDepartmentRepository jobDepartmentRepository;

    @Test
    public void createJobDepartment() {
        JobDepartment jobDepartment = new JobDepartment();
        jobDepartment.setJobDepartment("INTERN_TESTER");
        jobDepartment.setDescription("INTERN_TESTER");
        jobDepartment.setName("INTERN_TESTER");
        jobDepartment.setSalaryRange(300000);

        JobDepartment savedJobDepartment = jobDepartmentRepository.save(jobDepartment);

        assertThat(savedJobDepartment.getId()).isGreaterThan(0);

    }

}
