package com.timesheet.utilities;

import com.manage.employeemanagementmodel.entity.RefreshToken;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefreshTokenUtil {

    @Value("${refreshtoken.expiration}")
    private long REFRESH_TOKEN_EXPIRED_DATE;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        String generatedString = RandomStringUtils.randomAlphabetic(500);
        refreshToken.setToken(generatedString);
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRED_DATE));
        return refreshToken;
    }
}
