package com.kyc.utilities;

import com.kyc.entities.User;
import com.kyc.repositories.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import java.util.Optional;

@Configuration
@Profile("dev")
@PropertySource(value = {"classpath:application-secret.yml"})
public class securityConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    @Bean
    public long jwtExpiration() {
        return jwtExpiration;
    }


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
