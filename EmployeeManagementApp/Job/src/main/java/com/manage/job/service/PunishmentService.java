package com.manage.job.service;

import com.manage.employeemanagementmodel.entity.Employee;

import java.util.List;

public interface PunishmentService {
    void checkPunishmentPerDay(List<Employee> employees);
}
