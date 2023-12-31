package com.manage.note;

import static org.assertj.core.api.Assertions.assertThat;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.Task;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;
import com.manage.employee.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class NoteRepositoryTests {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void createTimesheetForEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        WorkingType workingType = WorkingType.ONSITE;
        TimeSheetStatus status = TimeSheetStatus.PENDING;
        Task task = new Task();
        task.setId(7);
        Note note = new Note();
        note.setNote("*daily");
        note.setEmployee(employee);
        note.setTask(task);
        note.setWorkingType(workingType);
        note.setStatus(status);
        note.setWorkingTime(8);
        String dateString = "13-06-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        note.setDateSubmit(date);

        Note savedNote = noteRepository.save(note);

        assertThat(savedNote.getId()).isGreaterThan(0);

    }

}
