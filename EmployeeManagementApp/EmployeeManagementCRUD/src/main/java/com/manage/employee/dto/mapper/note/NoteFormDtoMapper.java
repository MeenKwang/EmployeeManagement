package com.manage.employee.dto.mapper.note;

import com.manage.employee.dto.NoteFormDto;
import com.manage.employeemanagementmodel.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteFormDtoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dateSubmit", source = "dateSubmit")
    @Mapping(target = "workingTime", source = "workingTime")
    @Mapping(target = "workingType", source = "workingType")
    @Mapping(target = "employee.id", source = "employeeId")
    @Mapping(target = "task.id", source = "taskId")
    Note noteFormDtoToNoteMapper(NoteFormDto noteFormDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dateSubmit", source = "dateSubmit")
    @Mapping(target = "workingTime", source = "workingTime")
    @Mapping(target = "workingType", source = "workingType")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "taskId", source = "task.id")
    NoteFormDto noteToNoteFormDtoMapper(Note note);
}
