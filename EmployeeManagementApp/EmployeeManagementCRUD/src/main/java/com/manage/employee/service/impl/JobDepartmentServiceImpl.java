package com.manage.employee.service.impl;

import com.manage.employee.repository.JobDepartmentRepository;
import com.manage.employee.service.JobDepartmentService;
import com.manage.employeemanagementmodel.entity.JobDepartment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDepartmentServiceImpl implements JobDepartmentService {

    private final JobDepartmentRepository jobDepartmentRepository;

    public JobDepartmentServiceImpl(JobDepartmentRepository jobDepartmentRepository) {
        this.jobDepartmentRepository = jobDepartmentRepository;
    }

    @Override
    public List<JobDepartment> findAll() {
        return jobDepartmentRepository.findAll();
    }
}
