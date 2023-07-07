package com.timesheet.controller;

import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.service.CheckInService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("save_checkpoint_time")
    public ResponseEntity<?> saveCheckpointTime(@RequestParam("employeeId") Integer employeeId,
                                                @RequestParam("checkPointTime") String checkPointTime) {
        try {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.parse(checkPointTime), ZoneId.systemDefault());
            System.out.println(localDateTime);
            return ResponseEntity.ok(checkInService.saveCheckPointInDay(employeeId, localDateTime));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }
}
