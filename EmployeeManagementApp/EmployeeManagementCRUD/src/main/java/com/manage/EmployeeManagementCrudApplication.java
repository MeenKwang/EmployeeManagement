package com.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan({"com.manage.employeemanagementmodel.entity"})
public class EmployeeManagementCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementCrudApplication.class, args);
	}

}
