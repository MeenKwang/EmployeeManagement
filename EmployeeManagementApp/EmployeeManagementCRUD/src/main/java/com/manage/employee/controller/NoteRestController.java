package com.manage.employee.controller;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employee.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteRestController {

    private final NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("notes_by_week")
    public ResponseEntity<List<Note>> getNotesListByWeekNumber(@RequestParam("employeeId") Integer employeeId,
                                                               @RequestParam("weekNumber") Integer weekNumber) {
        List<Note> lst = noteService.listTimesheetPerWeekNumber(employeeId, weekNumber);

        return ResponseEntity.ok().body(lst);
    }
}
