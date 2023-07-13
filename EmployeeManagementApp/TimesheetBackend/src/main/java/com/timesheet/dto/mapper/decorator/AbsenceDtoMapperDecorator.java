package com.timesheet.dto.mapper.decorator;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.AbsenceTypeOff;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.dto.mapper.AbsenceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbsenceDtoMapperDecorator implements AbsenceDtoMapper {
    @Autowired
    @Qualifier("delegate")
    private AbsenceDtoMapper delegate;

    @Override
    public Absence absenceDtoToAbsence(AbsenceDto absenceDto) {
        Absence absence = delegate.absenceDtoToAbsence(absenceDto);
        if(absenceDto.getAbsenceTypeOffId() == null) {
            absence.setTypeOff(null);
        } else {
            AbsenceTypeOff absenceTypeOff = new AbsenceTypeOff();
            absenceTypeOff.setId(absenceDto.getAbsenceTypeOffId());
            absence.setTypeOff(absenceTypeOff);
        }
        return absence;
    }
}
