package com.manage.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manage.employeemanagementmodel.entity.JobDetails;

public interface JobDetailsRepository extends JpaRepository<JobDetails, Integer>{

}
