package com.kyc.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class securityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf-> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/**").permitAll()  // Allow access to public paths without authentication
                .anyRequest().permitAll();  // Any other requests require authentication

        return http.build();
    }
}
