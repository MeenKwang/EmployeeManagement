package com.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.manage.employeemanagementmodel.entity"})
public class TimesheetBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimesheetBackendApplication.class, args);
    }

}
