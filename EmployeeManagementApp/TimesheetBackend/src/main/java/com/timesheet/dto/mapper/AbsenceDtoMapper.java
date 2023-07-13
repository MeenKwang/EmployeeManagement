package com.timesheet.dto.mapper;

import com.manage.employeemanagementmodel.entity.Absence;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.dto.mapper.decorator.AbsenceDtoMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(AbsenceDtoMapperDecorator.class)
public interface AbsenceDtoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "absenceType.id", source = "absenceTypeId")
    @Mapping(target = "reason" , source = "reason")
    @Mapping(target = "dateRequest" , source = "dateRequest")
    @Mapping(target = "dateSubmit" , source = "dateSubmit")
    @Mapping(target = "typeTimeOff" , source = "typeTimeOff")
    @Mapping(target = "timeOff" , source = "timeOff")
    @Mapping(target = "employee.id" , source = "employeeId")
    Absence absenceDtoToAbsence(AbsenceDto absenceDto);

}
