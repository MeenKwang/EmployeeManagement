package com.manage.employee.service;

import java.time.LocalDate;
import java.util.List;

import com.manage.employee.dto.NoteDto;
import com.manage.employee.dto.NoteFormDto;
import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;

public interface NoteService {
	List<Note> listAllNoteByEmployee(Integer employeeId);
	List<Note> listTimesheetPerWeekNumber(Integer employeeId, int weekNumber);
	boolean submitWeekForApproved(Integer selectedWeek, Integer employeeId);
	boolean deleteNoteById(Integer noteId) throws NoteNotFoundException;
	void saveNote(NoteFormDto noteFormDto);
	boolean approveNoteRequestsForEmployee(Integer noteId) throws NoteNotFoundException;
	List<NoteDto> listTimesheetPerMonthOfEmployeeForManager(Integer employeeId, int monthNumber);
}
