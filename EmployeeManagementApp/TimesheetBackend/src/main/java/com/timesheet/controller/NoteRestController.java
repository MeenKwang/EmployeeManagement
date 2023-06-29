package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Note;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.service.NoteService;
import com.timesheet.service.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/notes")
public class NoteRestController {

    private final NoteService noteService;
    private final PermissionService permissionService;

    public NoteRestController(NoteService noteService, PermissionService permissionService) {
        this.noteService = noteService;
        this.permissionService = permissionService;
    }
//    @PreAuthorize("hasAnyAuthority(@permissionService.getApiPermission(#request.getRequestURL().toString()))")
    @GetMapping("notes_by_week")
    public ResponseEntity<List<Note>> getNotesListByWeekNumber(@RequestParam("employeeId") Integer employeeId,
                                                               @RequestParam("weekNumber") Integer weekNumber,
                                                               HttpServletRequest request) {
        List<Note> lst = noteService.listTimesheetPerWeekNumber(employeeId, weekNumber);

        return ResponseEntity.ok().body(lst);
    }

    @PostMapping("save")
    public ResponseEntity<?> saveNote(@RequestBody NoteFormDto noteFormDto) {
        try {
            Note note = noteService.saveNote(noteFormDto);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("delete")
    public ResponseEntity<?> deleteNoteById(@RequestParam("noteId") Integer noteId) {
        try {
            noteService.deleteNote(noteId);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("submit_week_for_approved")
    public ResponseEntity<?> submitTimesheetWeek(@RequestParam("currentWeekNumber") Integer currentWeekNumber) {
        try {
            noteService.updateNewNotesStatusToPendingStatus(currentWeekNumber);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
}