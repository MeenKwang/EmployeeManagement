package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.NoteSummaryDto;
import com.timesheet.dto.NoteViewDto;
import com.timesheet.dto.NotesPerDayDto;
import com.timesheet.dto.mapper.NoteFormDtoMapper;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;
import com.timesheet.repository.NoteRepository;
import com.timesheet.service.NoteService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteFormDtoMapper noteFormDtoMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteFormDtoMapper noteFormDtoMapper) {
        this.noteRepository = noteRepository;
        this.noteFormDtoMapper = noteFormDtoMapper;
    }
    @Override
    @Cacheable(value = "timesheets", key = "#employeeId")
    public List<NotesPerDayDto> listTimesheetPerWeekNumber(Integer employeeId, Integer weekNumber) {
        List<NoteViewDto> noteViewDtos = noteRepository.listNotesByWeekNumber(employeeId, weekNumber);
        Map<LocalDate, List<NoteViewDto>> notesPerDate = noteViewDtos.stream().collect(Collectors.groupingBy(NoteViewDto::getDateSubmit));
        List<NotesPerDayDto> notesPerDayDtoList = new ArrayList<>();
        for(Map.Entry<LocalDate, List<NoteViewDto>> entry : notesPerDate.entrySet()) {
            notesPerDayDtoList.add(new NotesPerDayDto(entry.getKey(), entry.getValue()));
        }
        notesPerDayDtoList.sort(Comparator.comparing(NotesPerDayDto::getDateSubmit));
        return notesPerDayDtoList;
    }

    @Override
    public Note saveNote(NoteFormDto noteFormDto) {
        Note note = noteFormDtoMapper.noteFormDtoToNote(noteFormDto);
        return noteRepository.save(note);
    }

    @Override
    public boolean deleteNote(Integer noteId) throws NoteNotFoundException {
        boolean exist = noteRepository.existsById(noteId);
        if (!exist) {
            throw new NoteNotFoundException("Cannot find any note with the given information");
        } else {
            try {
                noteRepository.deleteById(noteId);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean updateNewNotesStatusToPendingStatus(Integer currentWeekNumber) {
        try {
            noteRepository.pendingAllNewTimesheetRequest(currentWeekNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @CachePut(value = "timesheets", key = "#{noteId}")
    public NoteFormDto getNoteFormById(Integer noteId) {
        return noteRepository.getNoteFormById(noteId);
    }

    @Override
    public List<NoteSummaryDto> getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(NoteSummaryRequestDto noteSummaryRequestDto) {
        return noteRepository.getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(noteSummaryRequestDto);
    }

    @Override
    public Long getOpenTalkCount(CheckInRequestDto request) {
        return noteRepository.getOpenTalkCount(request);
    }

    @Override
    public Map<TimeSheetStatus, List<NoteViewDto>> listAllPendingTimesheetOfStaffInParticularMonthAndYear(int staffId, int month, int year) {
        List<NoteViewDto> noteViewDtoList = noteRepository.listAllPendingTimesheetOfStaffInParticularMonth(staffId, month, year);
        Map<TimeSheetStatus, List<NoteViewDto>> map = noteViewDtoList.stream().collect(Collectors.groupingBy(NoteViewDto::getStatus, Collectors.toList()));
        return map;
    }

    @Override
    public void updatePendingTimesheetStatus(int timesheetId, TimeSheetStatus status) {
        noteRepository.updatePendingTimesheetStatus(timesheetId, status);
    }
}
