package com.manage.job;

import com.manage.job.event_example.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan({"com.manage.employeemanagementmodel.entity"})
@EnableScheduling
public class JobApplication {

    private final CustomEventPublisher publisher;

    public JobApplication(CustomEventPublisher publisher) {
        this.publisher = publisher;
    }

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            System.out.println("Calculate something 1");
            publisher.publishCustomEvent("Running event for listening");
            System.out.println("Calculate something 2");
        };
    }

}
