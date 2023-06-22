package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_department")
public class JobDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "job_department", nullable = false, length = 50)
	private String jobDepartment;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "description", nullable = false, length = 255)
	private String description;
	@Column(name = "salary_range", nullable = false, length = 50)
	private long salaryRange;

	public JobDepartment() {
	}

	public JobDepartment(Integer id, String jobDepartment, String name, String description, long salaryRange) {
		this.id = id;
		this.jobDepartment = jobDepartment;
		this.name = name;
		this.description = description;
		this.salaryRange = salaryRange;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobDepartment() {
		return jobDepartment;
	}

	public void setJobDepartment(String jobDepartment) {
		this.jobDepartment = jobDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(long salaryRange) {
		this.salaryRange = salaryRange;
	}
}
