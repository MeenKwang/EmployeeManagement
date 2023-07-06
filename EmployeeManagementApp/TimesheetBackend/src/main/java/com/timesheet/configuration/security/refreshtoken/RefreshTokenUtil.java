package com.timesheet.configuration.security.refreshtoken;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.RefreshToken;
import com.timesheet.configuration.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefreshTokenUtil {

    @Value("${refreshtoken.expiration}")
    private long REFRESH_TOKEN_EXPIRED_DATE;
    @Value("${refreshtoken.secret}")
    private String SECRET_KEY;

    public RefreshToken generateRefreshToken(CustomUserDetails account) {
        String refreshTokenString = Jwts.builder()
                .setSubject(String.format("%s", account.getUsername()))
                .claim("roles", account.getAuthorities().toString())
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRED_DATE))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRED_DATE));
        return refreshToken;
    }
}
