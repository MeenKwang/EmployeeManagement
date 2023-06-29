package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteFormDto;

import java.util.List;

public interface NoteService {
    List<Note> listTimesheetPerWeekNumber(Integer employeeId, Integer weekNumber);
    Note saveNote(NoteFormDto noteFormDto);
    boolean deleteNote(Integer noteId) throws NoteNotFoundException;
    boolean updateNewNotesStatusToPendingStatus(Integer currentWeekNumber);
}
