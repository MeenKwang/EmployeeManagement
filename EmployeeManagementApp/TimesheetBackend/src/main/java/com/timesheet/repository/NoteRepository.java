package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Note;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.NoteViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("SELECT note FROM Note note WHERE note.employee.id = :id")
    List<Note> findAllByEmployeeId(@Param("id") Integer id);
    @Query("SELECT new com.timesheet.dto.NoteViewDto(note.id, note.task.project.name, note.task.name, note.note, note.workingTime, note.dateSubmit, note.status) " +
            "FROM Note note WHERE WEEK(note.dateSubmit) = :weekNumber AND note.employee.account.username = :username ORDER BY note.dateSubmit ASC")
    List<NoteViewDto> listNotesByWeekNumber(@Param("username") String username, @Param("weekNumber") int weekNumber);
    @Query("SELECT note FROM Note note WHERE MONTH(note.dateSubmit) = :month AND note.employee.id = :employeeId")
    List<Note> listNotesOfEmployeeByMonth(@Param("employeeId") Integer employeeId, @Param("month") int month);
    @Modifying
    @Query("UPDATE Note note SET note.status = com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus.PENDING " +
            "WHERE WEEK(note.dateSubmit) BETWEEN (?1 - 1) AND (?1)")
    void pendingAllNewTimesheetRequest(Integer currentWeekNumber);
    @Query("SELECT new com.timesheet.dto.NoteFormDto(note.id, note.employee.id, note.task.project.id, note.task.id, note.note, note.workingTime, note.workingType, note.dateSubmit, note.status) " +
            "FROM Note note WHERE note.id = ?1")
    NoteFormDto getNoteFormById(@Param("id") Integer id);
}
