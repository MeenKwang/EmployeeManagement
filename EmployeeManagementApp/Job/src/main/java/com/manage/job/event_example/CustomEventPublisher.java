package com.manage.job.event_example;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    public void publishCustomEvent(final String message) {
        System.out.println("Publishing custom event. ");
        CustomEvent customSpringEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
