package com.timesheet.controller;

import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.service.CheckInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/checkin")
public class CheckInRestController {
    private final CheckInService checkInService;

    public CheckInRestController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("checkin_per_month")
    public ResponseEntity<?> getCheckinPerMonth(@RequestBody CheckInRequestDto request) {
        try {
            return ResponseEntity.ok(checkInService.getAllCheckInBySpecificMonthAndYear(request));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }
}
