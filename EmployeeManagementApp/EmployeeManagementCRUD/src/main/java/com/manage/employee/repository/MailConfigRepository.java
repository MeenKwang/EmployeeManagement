package com.manage.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manage.employee.entity.MailConfig;
import com.manage.employee.entity.enums.MailType;

public interface MailConfigRepository extends JpaRepository<MailConfig, Integer> {

	MailConfig findByType(MailType notifyEmployee);
	
}
