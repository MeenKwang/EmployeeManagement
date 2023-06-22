package com.manage.employee.service;

import com.manage.employee.entity.Employee;

import jakarta.mail.MessagingException;

public interface EmailService {
	void sendEmailToPM(Employee employee, String type) throws MessagingException;
}
