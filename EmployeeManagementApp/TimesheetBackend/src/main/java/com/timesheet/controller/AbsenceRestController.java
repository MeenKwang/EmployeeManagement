package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.timesheet.dto.AbsenceDto;
import com.timesheet.service.AbsenceService;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("/app/absence")
public class AbsenceRestController {
    private final AbsenceService absenceService;

    public AbsenceRestController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @PutMapping("update_absence_status")
    public ResponseEntity<?> updateAbsenceStatus(@RequestParam("status") AbsenceStatus status, @RequestParam("id") Integer id) {
        try {
            absenceService.updateAbsenceStatus(id, status);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }
    @GetMapping("get_absence_of_month")
    public ResponseEntity<?> getAbsenceStatus(@RequestParam("month") Integer monthNumber,
                                              @RequestParam("year") Integer yearNumber,
                                              @RequestParam("employeeId") Integer employeeId) {
        return ResponseEntity.ok(absenceService.listAllAbsenceRequestInMonthAndYearOfEmployee(monthNumber, yearNumber, employeeId));
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAbsenceRequest(@RequestBody AbsenceDto absenceDto) {
        try {
            absenceService.saveAbsenceRequest(absenceDto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("get_day_absence_list")
    public ResponseEntity<?> getDayAbsenceList(@RequestParam("month") Integer month,
                                               @RequestParam("year") Integer year,
                                               @RequestParam("employeeId") Integer employeeId) {
        return ResponseEntity.ok(absenceService.ListAllDayAbsenceInParticularMonthAndYearOfEmployee(month, year, employeeId));
    }

    @GetMapping("get_absences_per_day")
    public ResponseEntity<?> getAbsencesPerDay(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date date,
                                               @RequestParam("employeeId") Integer employeeId) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ResponseEntity.ok(absenceService.getAbsenceByDateAndEmployee(localDate, employeeId));
    }

    @GetMapping("find_by_id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(absenceService.findFormById(id));
    }
    @DeleteMapping("delete_by_id")
    public ResponseEntity<Boolean> deleteById(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(absenceService.deletePendingAbsenceRequest(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("staff_absence_by_month")
    public ResponseEntity<?> getStaffAbsenceByMonthAndYear(@RequestParam("staffId") Integer staffId,
                                                             @RequestParam("month") Integer month,
                                                             @RequestParam("year") Integer year) {
        return ResponseEntity.ok().body(absenceService.listAllAbsenceOfStaffInParticularMonthAndYear(staffId, month, year));
    }

    @PutMapping("update_staff_absence_status")
    public ResponseEntity<?> updateAbsenceStatus(@RequestParam("absenceId") Integer noteId, @RequestParam("status") AbsenceStatus status) {
        absenceService.updateAbsenceStatus(noteId, status);
        return ResponseEntity.ok(true);
    }

}
