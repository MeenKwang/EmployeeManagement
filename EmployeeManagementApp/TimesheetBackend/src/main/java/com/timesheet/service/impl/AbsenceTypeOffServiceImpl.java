package com.timesheet.service.impl;

import com.timesheet.dto.AbsenceTypeOffSelectDto;
import com.timesheet.repository.AbsenceTypeOffRepository;
import com.timesheet.service.AbsenceTypeOffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AbsenceTypeOffServiceImpl implements AbsenceTypeOffService {

    private final AbsenceTypeOffRepository absenceTypeOffRepository;

    public AbsenceTypeOffServiceImpl(AbsenceTypeOffRepository absenceTypeOffRepository) {
        this.absenceTypeOffRepository = absenceTypeOffRepository;
    }

    @Override
    public List<AbsenceTypeOffSelectDto> listAllAbsenceTypeOffByAbsenceTypeName(String absenceTypeName) {
        try {
            return absenceTypeOffRepository.listAllAbsenceTypeOffByAbsenceTypeName(absenceTypeName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
