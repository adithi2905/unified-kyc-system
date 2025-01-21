package com.kyc.service;

import com.kyc.dto.LoginDto;
import com.kyc.dto.UserDto;


import com.kyc.entities.User;
import com.kyc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    public UserRepository userRepo;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void registerUser(UserDto request) {
        // Save user
        User user = new User();
        user.setName(request.getName());
        user.setContact(request.getContact());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder().encode(request.getPassword()));
        userRepo.save(user);
    }

    public boolean loginUser(LoginDto loginDto){
        User user = userRepo.findByEmailOrName(loginDto.getNameOremail(),loginDto.getNameOremail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return passwordEncoder().matches(loginDto.getPassword(), user.getPassword());
    }

}
