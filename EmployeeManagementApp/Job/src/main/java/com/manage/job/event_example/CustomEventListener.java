package com.manage.job.event_example;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    @Async
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("Doing first action from the first listener...");
        try {
            Thread.sleep(2000);
            System.out.println("Received spring custom event from the first listener - " + event.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Doing second action from the first listener...");
    }
}
