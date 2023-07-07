package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.timesheet.service.AbsenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
