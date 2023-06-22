package com.manage.employee.service.impl;

import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.manage.employee.dto.NoteDto;
import com.manage.employee.dto.NoteFormDto;
import com.manage.employee.dto.mapper.note.NoteFormDtoMapper;
import com.manage.employee.entity.enums.TimeSheetStatus;
import com.manage.employee.exception.NoteNotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.employee.entity.Note;
import com.manage.employee.repository.NoteRepository;
import com.manage.employee.service.NoteService;
@Service
@Transactional
public class NoteServiceImpl implements NoteService{
	
	private final NoteRepository noteRepository;
	private final NoteFormDtoMapper noteFormDtoMapper;
	
	public NoteServiceImpl(NoteRepository noteRepository, NoteFormDtoMapper noteFormDtoMapper) {
		this.noteRepository = noteRepository;
		this.noteFormDtoMapper = noteFormDtoMapper;
	}

	@Override
	public List<Note> listAllNoteByEmployee(Integer employeeId) {
		System.out.println(employeeId);
		return noteRepository.findAllByEmployeeId(employeeId);
	}

	@Override
	public List<Note> listTimesheetPerWeekNumber(Integer employeeId, int weekNumber) {
		List<Note> lst = noteRepository.listNotesByWeekNumber(employeeId, weekNumber);
		return lst;
	}

	@Override
	public boolean submitWeekForApproved(Integer selectedWeekNumber, Integer employeeId) {
		LocalDate currentDate = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int currentWeekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
		if(currentWeekNumber > selectedWeekNumber) return false;
		List<Note> notes= noteRepository.listNotesByWeekNumber(employeeId, selectedWeekNumber);
		for(Note note : notes) {
			if(note.getStatus() == TimeSheetStatus.NEW) {
				note.setStatus(TimeSheetStatus.PENDING);
			}
		}
		return true;
	}

	@Override
	public boolean deleteNoteById(Integer noteId) throws NoteNotFoundException {
		Note note = noteRepository.findById(noteId).get();
		if(note == null) {
			throw new NoteNotFoundException("Cannot find note with this id: " + noteId);
		} else {
			if(note.getStatus() == TimeSheetStatus.APPROVED || note.getStatus() == TimeSheetStatus.REJECT) return false;
			LocalDate currentDate = LocalDate.now();
			WeekFields weekFields = WeekFields.of(Locale.getDefault());
			int currentWeekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
			int noteWeekNumber = note.getDateSubmit().get(weekFields.weekOfWeekBasedYear());
			if(currentWeekNumber != noteWeekNumber) return false;
			note.setEmployee(null);
			note.setTask(null);
			try {
				noteRepository.delete(note);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	@Override
	public void saveNote(NoteFormDto noteFormDto) {
		Note note = noteFormDtoMapper.noteFormDtoToNoteMapper(noteFormDto);
		try {
			if(note.getId() == null) note.setStatus(TimeSheetStatus.NEW);
			noteRepository.save(note);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean approveNoteRequestsForEmployee(Integer noteId) throws NoteNotFoundException {
		Note note = noteRepository.findById(noteId).get();
		if(note == null) {
			throw new NoteNotFoundException("Cannot find note with this id: " + noteId);
		}
		LocalDate currentDate = LocalDate.now();
		int currentMonth = currentDate.getMonthValue();
		if(currentMonth == note.getDateSubmit().getMonthValue()) {
			if(note.getStatus() == TimeSheetStatus.NEW) {
				note.setStatus(TimeSheetStatus.PENDING);
				try {
					Note savedNote = noteRepository.save(note);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<NoteDto> listTimesheetPerMonthOfEmployeeForManager(Integer employeeId, int monthNumber) {
		LocalDate currentDate = LocalDate.now();
		int currentMonth = currentDate.getMonthValue();
		List<Note> notes = noteRepository.listNotesOfEmployeeByMonth(employeeId, monthNumber);
		return null;
	}

}
