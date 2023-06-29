package com.timesheet.dto.mapper;

import com.manage.employeemanagementmodel.entity.Absence;
import com.timesheet.dto.AbsenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AbsenceDtoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "reason" , source = "reason")
    @Mapping(target = "typeOff.id" , source = "absenceTypeOffId")
    @Mapping(target = "dateRequest" , source = "dateSubmit")
    @Mapping(target = "timeOff" , source = "timeOff")
    @Mapping(target = "employee.id" , source = "employeeId")
    Absence absenceDtoToAbsence(AbsenceDto absenceDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "reason" , source = "reason")
    @Mapping(target = "absenceTypeOffId" , source = "typeOff.id")
    @Mapping(target = "dateSubmit" , source = "dateRequest")
    @Mapping(target = "timeOff" , source = "timeOff")
    @Mapping(target = "employeeId" , source = "employee.id")
    AbsenceDto absenceToAbsenceDto(Absence absence);
}
