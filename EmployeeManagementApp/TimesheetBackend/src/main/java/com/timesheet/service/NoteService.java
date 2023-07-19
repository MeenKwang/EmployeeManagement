package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.NoteSummaryDto;
import com.timesheet.dto.NoteViewDto;
import com.timesheet.dto.NotesPerDayDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface NoteService {
    List<NotesPerDayDto> listTimesheetPerWeekNumber(Integer employeeId, Integer weekNumber);
    Note saveNote(NoteFormDto noteFormDto);
    boolean deleteNote(Integer noteId) throws NoteNotFoundException;
    boolean updateNewNotesStatusToPendingStatus(Integer currentWeekNumber);
    NoteFormDto getNoteFormById(Integer noteId);
    List<NoteSummaryDto> getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(NoteSummaryRequestDto noteSummaryRequestDto);
    Long getOpenTalkCount(CheckInRequestDto request);
    Map<TimeSheetStatus, List<NoteViewDto>> listAllPendingTimesheetOfStaffInParticularMonthAndYear(int staffId, int month, int year);
    void updatePendingTimesheetStatus(int timesheetId, TimeSheetStatus status);
}
