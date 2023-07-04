package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.NoteViewDto;
import com.timesheet.dto.NotesPerDayDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface NoteService {
    List<NotesPerDayDto> listTimesheetPerWeekNumber(String username, Integer weekNumber);
    Note saveNote(NoteFormDto noteFormDto);
    boolean deleteNote(Integer noteId) throws NoteNotFoundException;
    boolean updateNewNotesStatusToPendingStatus(Integer currentWeekNumber);
    NoteFormDto getNoteFormById(Integer noteId);
}
