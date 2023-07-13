package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.AbsenceTypeOff;
import com.timesheet.service.AbsenceTypeOffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/absenceTypeOff")
public class AbsenceTypeOffRestController {
    private final AbsenceTypeOffService absenceTypeOffService;

    public AbsenceTypeOffRestController(AbsenceTypeOffService absenceTypeOffService) {
        this.absenceTypeOffService = absenceTypeOffService;
    }

    @GetMapping("get_absence_type_off_select")
    public ResponseEntity<?> findAllByAbsenceTypeName(@RequestParam("absenceTypeName") String absenceTypeName) {
        try {
            return ResponseEntity.ok(absenceTypeOffService.listAllAbsenceTypeOffByAbsenceTypeName(absenceTypeName));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }
}
