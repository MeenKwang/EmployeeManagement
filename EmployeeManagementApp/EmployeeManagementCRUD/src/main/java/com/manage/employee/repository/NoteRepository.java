package com.manage.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manage.employeemanagementmodel.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	@Query("SELECT note FROM Note note WHERE note.employee.id = :id")
	List<Note> findAllByEmployeeId(@Param("id") Integer id);

	@Query("SELECT note FROM Note note WHERE WEEK(note.dateSubmit) = :weekNumber AND note.employee.id = :employeeId")
	List<Note> listNotesByWeekNumber(@Param("employeeId") Integer employeeId, @Param("weekNumber") int weekNumber);
	@Query("SELECT note FROM Note note WHERE MONTH(note.dateSubmit) = :month AND note.employee.id = :employeeId")
	List<Note> listNotesOfEmployeeByMonth(@Param("employeeId") Integer employeeId, @Param("month") int month);

}
