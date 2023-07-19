package com.timesheet.configuration.security.jwt;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.Role;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.dto.RoleTokenDto;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    @Value("${jwt.expiration}")
    private long TOKEN_EXPIRED_DAY;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /*
        HEADER:ALGORITHM & TOKEN TYPE
        {
          "alg": "HS512",
          "typ": "JWT"
        }
        PAYLOAD:DATA
        {
          "sub": "1234567890",
          "name": "John Doe",
          "iat": 1516239022
        }
        VERIFY SIGNATURE
        HMACSHA512(
          base64UrlEncode(header) + "." +
          base64UrlEncode(payload),
          your-512-bit-secret
        )
    */
    public String generateAccessToken(CustomUserDetails account) {
        String accessToken = Jwts.builder()
                .setSubject(String.format("%s", account.getUsername()))
                .claim("roles", account.getAuthorities().toString())
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRED_DAY))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return accessToken;
    }

    public String generateAccessTokenUsingField(Account account) {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        account.getRoles().stream().forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });
        String accessToken = Jwts.builder()
                .setSubject(String.format("%s", account.getUsername()))
                .claim("roles", roles.toString())
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRED_DAY))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return accessToken;
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        String token = header.split(" ")[1].trim();

        return token;
    }

    public void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Account getAccount(String token) {
        Account account = new Account();

        Claims claims = parseClaims(token);
        String claimRoles = (String) claims.get("roles");
        claimRoles = claimRoles.replace("[", "").replace("]", "");
        String[] roleNames = claimRoles.split(", ");
        List<Role> roles = new ArrayList<>();
        for(String roleName : roleNames) {
            Role role = new Role();
            role.setName(roleName);
            roles.add(role);
        }
        account.setRoles(roles);
        String subject = (String) claims.get(Claims.SUBJECT);
        String username = subject;

        account.setUsername(username);
        return account;
    }

    public UserDetails getUserDetails(String token) {
        Account account = getAccount(token);
        return new CustomUserDetails(account);
    }
}
