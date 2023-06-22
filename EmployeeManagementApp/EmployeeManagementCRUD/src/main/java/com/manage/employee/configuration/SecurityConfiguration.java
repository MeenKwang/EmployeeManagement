package com.manage.employee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	@Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
	
    //configure HttpSecurity
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf(customize -> customize.disable())
        	.cors(customize -> customize.disable())
        	.httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                    .requestMatchers("/employees").hasAnyAuthority("PM", "HR")
                    .requestMatchers("employees/new", "employees/edit/*", "/delete/*").hasAuthority("HR")
                    .anyRequest().authenticated()
            )
        	.formLogin(customizer -> customizer.loginPage("/login").usernameParameter("username").passwordParameter("password")
        			.permitAll()
            )
        	.logout(customize -> customize.permitAll())
                .rememberMe(customize -> customize.key("uniqueAndSecret").tokenValiditySeconds(86400 * 30));
//        	.authorizeHttpRequests(customize -> customize.anyRequest().permitAll());

        return http.build();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    @Bean
    PasswordEncoder passwordEncoder () {
    	return new BCryptPasswordEncoder();
    }
// In Memory Users for Testing    
//    @Bean
//    UserDetailsService users() {
//    	UserDetails user = User.builder()
//    		.username("user")
//    		.password("$2a$10$xnrtm3n3gp23EJZ9CnM1qOoVdxqYpZWuW2AqZGL0RKKVvSs61KiVK")
//    		.roles("USER")
//    		.build();
//    	UserDetails admin = User.builder()
//    		.username("admin")
//    		.password("$2a$10$xnrtm3n3gp23EJZ9CnM1qOoVdxqYpZWuW2AqZGL0RKKVvSs61KiVK")
//    		.roles("USER", "ADMIN")
//    		.build();
//    	return new InMemoryUserDetailsManager(user, admin);
//    }

    //configure WebSecurity
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
