package com.timesheet.controller;

import com.timesheet.service.EmployeeService;
import com.timesheet.service.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/profile")
public class ProfileRestController {

    private final EmployeeService employeeService;
    private final PermissionService permissionService;

    public ProfileRestController(EmployeeService employeeService, PermissionService permissionService) {
        this.employeeService = employeeService;
        this.permissionService = permissionService;
    }

    @GetMapping("")
//    @PreAuthorize("hasAnyAuthority(@permissionService.getApiPermission(#request.getRequestURL().toString()))")
    public ResponseEntity<?> getMyProfile(@RequestParam(value = "username") String accountUsername, HttpServletRequest request) {
        return ResponseEntity.ok(employeeService.getEmployeeInfo(accountUsername));
    }
}
