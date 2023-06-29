package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.exception.AbsenceRequestNotFoundException;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.dto.mapper.AbsenceDtoMapper;
import com.timesheet.repository.AbsenceRepository;
import com.timesheet.service.AbsenceService;

import java.util.List;

public class AbsenceServiceImpl implements AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final AbsenceDtoMapper absenceDtoMapper;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository, AbsenceDtoMapper absenceDtoMapper) {
        this.absenceRepository = absenceRepository;
        this.absenceDtoMapper = absenceDtoMapper;
    }

    @Override
    public List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId) {
        return absenceRepository.listAllAbsenceRequestInMonthAndYearWithAbsenceType(month, year, absenceTypeId);
    }

    @Override
    public Absence saveAbsenceRequest(AbsenceDto absenceDto) {
        Absence absenceRequest = absenceDtoMapper.absenceDtoToAbsence(absenceDto);
        return absenceRepository.save(absenceRequest);
    }

    @Override
    public boolean deletePendingAbsenceRequest(Integer absenceRequestId) throws AbsenceRequestNotFoundException {
        boolean exist = absenceRepository.existsById(absenceRequestId);
        if(exist) {
            try {
                absenceRepository.deleteById(absenceRequestId);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new AbsenceRequestNotFoundException("Cannot find any Absence Request with the given information");
        }
    }
}
