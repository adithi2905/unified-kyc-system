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

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    public UserRepository userRepo;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User registerUser(UserDto request) {
        // Save user
        User user = new User();

        user.setFullName(request.getName());
        user.setContact(request.getContact());
        user.setEmail(request.getEmail());
        if(request.getPassword().equals(request.getConfirmPassword())){
            user.setPassword(passwordEncoder().encode(request.getPassword()));
            userRepo.save(user);
        }
        return user;

    }

    public Optional<User> loginUser(LoginDto loginDto){
        Optional<User> user = userRepo.findByEmail(loginDto.getEmail());
        if(user.isPresent() && passwordEncoder().matches(loginDto.getPassword(), user.get().getPassword())){
            return user;
        }
        return Optional.empty();
    }

}
