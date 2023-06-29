package com.timesheet.configuration;

import com.timesheet.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AccountRepository accountRepository;

    public SecurityConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(accountRepository);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customize -> customize.disable());
        httpSecurity.cors(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.anyRequest().permitAll());

        return httpSecurity.build();
    }
}
