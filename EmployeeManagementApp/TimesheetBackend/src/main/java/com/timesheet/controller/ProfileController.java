package com.timesheet.controller;

import com.timesheet.service.EmployeeService;
import com.timesheet.service.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/profile")
public class ProfileController {

    private final EmployeeService employeeService;
    private final PermissionService permissionService;

    public ProfileController(EmployeeService employeeService, PermissionService permissionService) {
        this.employeeService = employeeService;
        this.permissionService = permissionService;
    }

    @GetMapping("")
//    @PreAuthorize("hasAnyAuthority(@permissionService.getApiPermission(#request.getRequestURL().toString()))")
    public ResponseEntity<?> getMyProfile(@RequestParam(value = "username") String accountUsername, HttpServletRequest request) {
        return ResponseEntity.ok(employeeService.getEmployeeInfo(accountUsername));
    }
}
