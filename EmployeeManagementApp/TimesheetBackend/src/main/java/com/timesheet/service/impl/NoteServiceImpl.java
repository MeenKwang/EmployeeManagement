package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.mapper.NoteFormDtoMapper;
import com.timesheet.repository.NoteRepository;
import com.timesheet.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Note> listTimesheetPerWeekNumber(Integer employeeId, Integer weekNumber) {
        return noteRepository.listNotesByWeekNumber(employeeId, weekNumber);
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
            throw new NoteNotFoundException("Cannot find any note with the given infomation");
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
}
