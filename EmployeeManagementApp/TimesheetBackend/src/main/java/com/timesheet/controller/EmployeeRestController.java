package com.timesheet.controller;

import com.timesheet.configuration.security.jwt.JwtTokenUtil;
import com.timesheet.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/employees")
public class EmployeeRestController {

    private final JwtTokenUtil jwtTokenUtil;
    private final EmployeeService employeeService;

    public EmployeeRestController(JwtTokenUtil jwtTokenUtil, EmployeeService employeeService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.employeeService = employeeService;
    }

    @GetMapping("employee_id")
    public ResponseEntity<?> getEmployeeId(HttpServletRequest request) {
        String accessToken = jwtTokenUtil.getAccessToken(request);
        String username = jwtTokenUtil.getSubject(accessToken);
        try {
            Integer employeeId = employeeService.getEmployeeId(username);
            return ResponseEntity.ok(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }
    @GetMapping("view_staff")
    public ResponseEntity<?> viewStaff(@RequestParam("buddyId") Integer buddyId, @RequestParam("pageNumber") Integer pageNumber,
                                       @RequestParam("pageSize") Integer pageSize, @RequestParam("nameSearch") String nameSearch,
                                       @RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder) {
        try {
            return ResponseEntity.ok(employeeService.getStaffListByNativeQuery(buddyId, pageNumber, pageSize, nameSearch, sortField, sortOrder));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }
}
