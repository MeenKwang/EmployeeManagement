package com.timesheet.configuration.security.jwt;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.Role;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.repository.AccountRepository;
import com.timesheet.service.PermissionService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;
//    private final PermissionService permissionService;

    public JwtTokenFilter(AccountRepository accountRepository, JwtTokenUtil jwtTokenUtil) {
        this.accountRepository = accountRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("START DO FILTER JTW");
        if(!hasAuthorizationHeader(request)) {
            System.out.println("hasAuthorizationHeader");
            filterChain.doFilter(request,response);
            return;
        }

        String accessToken = getAccessToken(request);

        if(!jwtTokenUtil.validateAccessToken(accessToken)) {
            System.out.println("jwtTokenUtil");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("ok");

        setAuthenticationContext(accessToken, request);

//        if(!isAuthorized(request)) {
//            System.out.println("Not authorized!");
//            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//            return;
//        }
        System.out.println("1" + response.getStatus());
        filterChain.doFilter(request, response);
        System.out.println("2" + response.getStatus());

    }

//    private boolean isAuthorized(HttpServletRequest request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String[] permissions = permissionService.getApiPermission(request.getRequestURL().toString());
//            if(permissions == null) return false;
//            List<String> permissionsList = Arrays.asList(permissions);
//            for(GrantedAuthority authority : userDetails.getAuthorities()) {
//                if(permissionsList.contains(authority.getAuthority())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        String token = header.split(" ")[1].trim();

        return token;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token) {
        Account account = new Account();

        Claims claims = jwtTokenUtil.parseClaims(token);
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

        return new CustomUserDetails(account);
    }
}
