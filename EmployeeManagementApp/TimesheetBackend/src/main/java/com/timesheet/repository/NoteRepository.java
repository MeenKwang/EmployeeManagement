package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.timesheet.dto.NoteFormDto;
import com.timesheet.dto.NoteSummaryDto;
import com.timesheet.dto.NoteViewDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;
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
    @Query("SELECT new com.timesheet.dto.NoteFormDto(note.id, note.employee.id, note.task.project.id, note.task.id, note.note, note.workingTime, note.workingType, note.dateSubmit, note.status, note.dateModify) " +
            "FROM Note note WHERE note.id = ?1")
    NoteFormDto getNoteFormById(@Param("id") Integer id);
    @Query("SELECT new com.timesheet.dto.NoteSummaryDto(note.dateSubmit, SUM(note.workingTime)) FROM Note note WHERE MONTH(note.dateSubmit) = :#{#request.month} AND YEAR(note.dateSubmit) = :#{#request.year} AND note.employee.id = :#{#request.employeeId}" +
            " AND note.status IN :#{#request.statuses} GROUP BY note.dateSubmit")
    List<NoteSummaryDto> getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(NoteSummaryRequestDto request);
    @Query("SELECT count(note) FROM Note note WHERE MONTH(note.dateSubmit) = :#{#request.month} AND YEAR(note.dateSubmit) = :#{#request.year} AND note.employee.id = :#{#request.employeeId} AND note.task.id = 7")
    Long getOpenTalkCount(CheckInRequestDto request);
    @Query("SELECT new com.timesheet.dto.NoteViewDto(note.id, note.task.project.name, note.task.name, note.note, note.workingTime, note.dateSubmit, note.status) " +
            "FROM Note note WHERE MONTH(note.dateSubmit) = ?2 AND YEAR(note.dateSubmit) = ?3 AND note.employee.id = ?1 ORDER BY note.dateSubmit ASC")
    List<NoteViewDto> listAllPendingTimesheetOfStaffInParticularMonth(int staffId, int month, int year);
    @Modifying
    @Query("UPDATE Note note SET note.status = ?2 " +
            "WHERE note.id = ?1")
    void updatePendingTimesheetStatus(int timesheetId, TimeSheetStatus status);

}
