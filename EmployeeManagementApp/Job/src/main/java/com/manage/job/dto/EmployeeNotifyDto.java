package com.manage.job.dto;

public class EmployeeNotifyDto {
    private Integer id;
    private String email;
    private String fullName;

    public EmployeeNotifyDto() {
    }

    public EmployeeNotifyDto(Integer id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
