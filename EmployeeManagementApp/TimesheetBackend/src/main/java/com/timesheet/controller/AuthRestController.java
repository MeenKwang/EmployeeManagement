package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.RefreshToken;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.configuration.security.jwt.JwtTokenUtil;
import com.timesheet.dto.AccountRequestDto;
import com.timesheet.dto.AccountResponseDto;
import com.timesheet.dto.RefreshTokenDto;
import com.timesheet.exception.RefreshTokenException;
import com.timesheet.service.AccountService;
import com.timesheet.service.EmployeeService;
import com.timesheet.service.RefreshTokenService;
import com.timesheet.configuration.security.refreshtoken.RefreshTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/app")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountService accountService;
    private final RefreshTokenUtil refreshTokenUtil;
    private final RefreshTokenService refreshTokenService;
    private final EmployeeService employeeService;

    public AuthRestController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, AccountService accountService, RefreshTokenUtil refreshTokenUtil, RefreshTokenService refreshTokenService, EmployeeService employeeService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
        this.refreshTokenUtil = refreshTokenUtil;
        this.refreshTokenService = refreshTokenService;
        this.employeeService = employeeService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Validated AccountRequestDto accountRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(accountRequestDto.getUsername(), accountRequestDto.getPassword())
            );
            CustomUserDetails account = (CustomUserDetails) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(account);
            Account myAccount = account.getAccount();
            RefreshToken refreshToken = refreshTokenUtil.generateRefreshToken(account);
            myAccount.setRefreshToken(refreshToken);
            accountService.save(myAccount);
            AccountResponseDto accountResponseDto = new AccountResponseDto();
            accountResponseDto.setEmail(account.getUsername());
            accountResponseDto.setEmployeeId(employeeService.getEmployeeId(account.getUsername()));
            accountResponseDto.setAccessToken(accessToken);
            accountResponseDto.setRefreshToken(refreshToken.getToken());
            return ResponseEntity.ok(accountResponseDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("refresh_token")
    public ResponseEntity<?> getRefreshToken(@RequestBody @Validated RefreshTokenDto refreshTokenBody) {
        System.out.println("Jump into refresh token api");
        String requestRefreshToken = refreshTokenBody.getRefreshToken();
        Account account = refreshTokenService.findByToken(requestRefreshToken);
        if(account.getUsername() != jwtTokenUtil.getSubject(requestRefreshToken)) {
            throw new RefreshTokenException(requestRefreshToken, "Refresh token is not valid!");
        }
        if(account.getRefreshToken().getExpiredDate().compareTo(new Date()) < 0) {
            throw new RefreshTokenException(requestRefreshToken, "Refresh token was expired. Please make a new sign in request");
        }
        if(account == null) {
            throw new RefreshTokenException(requestRefreshToken, "Refresh token is not in database!");
        }
        String accessToken = jwtTokenUtil.generateAccessTokenUsingField(account);
        return ResponseEntity.ok(null);
    }
}
