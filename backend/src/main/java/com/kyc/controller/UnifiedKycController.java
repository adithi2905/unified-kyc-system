package com.kyc.controller;

import com.kyc.dto.KycDetailsDto;
import com.kyc.dto.LoginDto;
import com.kyc.dto.UserDto;
import com.kyc.service.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kyc.service.AuthService;
@RestController
@RequestMapping("api/")
public class UnifiedKycController{

    @Autowired
    private AuthService authService;

    @Autowired
    private KycService kycService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        authService.registerUser(userDto);
        return ResponseEntity.ok ("User registered successfully") ;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        boolean isAuthenticated = authService.loginUser(loginDto);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitKycDetails(
            @ModelAttribute KycDetailsDto kycDetailsRequest) { // Handle MultipartFile
        kycService.processKycDetails(kycDetailsRequest);
        return ResponseEntity.ok("KYC details submitted successfully.");
    }
}