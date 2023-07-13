package com.timesheet.dto;

import org.springframework.beans.factory.annotation.Value;

public interface AbsenceTypeSelectDto {
    @Value("#{target.id}")
    Integer getId();
    @Value("#{target.name}}")
    String getName();
}
