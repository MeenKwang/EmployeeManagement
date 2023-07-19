package com.manage.job.controller;

import com.manage.job.service.CheckInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job/check_in")
public class CheckInRestController {
    private final CheckInService checkInService;

    public CheckInRestController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @GetMapping("employees")
    public ResponseEntity<?> getEmployeeIdNotCheckIn() {
        checkInService.generateCheckInPerDay();
        return ResponseEntity.ok(true);
    }
}
