package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Note;
import com.timesheet.configuration.security.jwt.JwtTokenUtil;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.NotesPerDayDto;
import com.timesheet.dto.ProjectSelectDto;
import com.timesheet.dto.TaskSelectDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;
import com.timesheet.service.NoteService;
import com.timesheet.service.PermissionService;
import com.timesheet.service.ProjectService;
import com.timesheet.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/notes")
public class NoteRestController {
    private final Logger LOGGER  = LoggerFactory.getLogger(NoteRestController.class);
    private final NoteService noteService;
    private final PermissionService permissionService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final JwtTokenUtil jwtTokenUtil;

    public NoteRestController(NoteService noteService, PermissionService permissionService, ProjectService projectService, TaskService taskService, JwtTokenUtil jwtTokenUtil) {
        this.noteService = noteService;
        this.permissionService = permissionService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
//    @PreAuthorize("hasAnyAuthority(@permissionService.getApiPermission(#request.getRequestURL().toString()))")
    @GetMapping("notes_by_week")
    public ResponseEntity<List<NotesPerDayDto>> getNotesListByWeekNumber(@RequestParam("username") String username,
                                                               @RequestParam("weekNumber") Integer weekNumber) {
        List<NotesPerDayDto> lst = noteService.listTimesheetPerWeekNumber(username, weekNumber);

        return ResponseEntity.ok().body(lst);
    }

    @PostMapping("save")
    public ResponseEntity<?> saveNote(@RequestBody NoteFormDto noteFormDto) {
        try {
            Note note = noteService.saveNote(noteFormDto);
        } catch (Exception e) {
            e.printStackTrace();
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
    @GetMapping("project_for_form")
    public ResponseEntity<?> getProjectForForm() {
        try {
            List<ProjectSelectDto> lst = projectService.getAllProjectsForForm();
            return ResponseEntity.ok(lst);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }
    @GetMapping("task_for_form")
    public ResponseEntity<?> getTaskForForm(@RequestParam("projectId") Integer projectId) {
        try {
            List<TaskSelectDto> lst = taskService.listAllTaskForFormByProjectId(projectId);
            return ResponseEntity.ok(lst);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("note_by_id")
    public ResponseEntity<?> getNoteById(@RequestParam("noteId") Integer noteId) {
        System.out.println("JUMP INTO THIS API!");
        try {
            NoteFormDto note = noteService.getNoteFormById(noteId);
            System.out.println("NOTE: " + note);
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("note_summary")
    public ResponseEntity<?> getNoteSummary(@RequestBody NoteSummaryRequestDto noteSummaryRequest) {
        try {
            return ResponseEntity.ok().body(noteService.getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(noteSummaryRequest));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("open_talk_count")
    public ResponseEntity<?> getOpenTalkCount(@RequestBody CheckInRequestDto request) {
        try {
            return ResponseEntity.ok().body(noteService.getOpenTalkCount(request));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }
}