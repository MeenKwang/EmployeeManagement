package com.timesheet.dto;

import java.time.LocalDate;

public class ProfileDto {
    private String fullName;
    private String companyEmail;
    private LocalDate dob;
    private String department;
    private String jobDepartment;

    public ProfileDto() {
    }

    public ProfileDto(String fullName, String companyEmail, LocalDate dob, String department, String jobDepartment) {
        this.fullName = fullName;
        this.companyEmail = companyEmail;
        this.dob = dob;
        this.department = department;
        this.jobDepartment = jobDepartment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
    }
}
