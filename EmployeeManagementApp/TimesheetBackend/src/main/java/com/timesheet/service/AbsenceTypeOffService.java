package com.timesheet.service;

import com.timesheet.dto.AbsenceTypeOffSelectDto;

import java.util.List;

public interface AbsenceTypeOffService {
    List<AbsenceTypeOffSelectDto> listAllAbsenceTypeOffByAbsenceTypeName(String absenceTypeName);
}
