package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("SELECT note FROM Note note WHERE note.employee.id = :id")
    List<Note> findAllByEmployeeId(@Param("id") Integer id);
    @Query("SELECT note FROM Note note WHERE WEEK(note.dateSubmit) = :weekNumber AND note.employee.id = :employeeId")
    List<Note> listNotesByWeekNumber(@Param("employeeId") Integer employeeId, @Param("weekNumber") int weekNumber);
    @Query("SELECT note FROM Note note WHERE MONTH(note.dateSubmit) = :month AND note.employee.id = :employeeId")
    List<Note> listNotesOfEmployeeByMonth(@Param("employeeId") Integer employeeId, @Param("month") int month);
    @Modifying
    @Query("UPDATE Note note SET note.status = com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus.PENDING " +
            "WHERE WEEK(note.dateSubmit) BETWEEN (?1 - 1) AND (?1)")
    void pendingAllNewTimesheetRequest(Integer currentWeekNumber);
}
