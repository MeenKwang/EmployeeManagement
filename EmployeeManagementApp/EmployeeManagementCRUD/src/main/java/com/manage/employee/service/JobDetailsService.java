package com.manage.employee.service;

import java.util.List;

import com.manage.employee.entity.JobDetails;

public interface JobDetailsService {
	List<JobDetails> findAll();
	JobDetails save(JobDetails jobDetails);
}
