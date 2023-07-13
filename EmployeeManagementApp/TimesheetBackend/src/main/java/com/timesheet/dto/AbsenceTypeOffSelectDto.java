package com.timesheet.dto;

import org.springframework.beans.factory.annotation.Value;

public interface AbsenceTypeOffSelectDto {
    @Value("#{target.id}")
    Integer getId();
    @Value("#{target.name}")
    String getName();
    @Value("#{target.description}")
    String getDescription();
}
