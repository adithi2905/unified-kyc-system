package com.kyc.controller;

import com.kyc.dto.KycDetailsDto;
import com.kyc.dto.LoginDto;
import com.kyc.dto.SSNResponse;
import com.kyc.dto.UserDto;
import com.kyc.dto.VCResponse;
import com.kyc.entities.GovernmentIssuedId;
import com.kyc.entities.User;
import com.kyc.service.KycService;
import com.kyc.service.SsnExtractionService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import com.kyc.service.AuthService;
import com.kyc.service.CertificateService;
import com.kyc.service.GenerateHash;
@RestController
@RequestMapping("/api")
public class UnifiedKycController{

    GovernmentIssuedId governmentIssuedId=new GovernmentIssuedId();

    @Autowired
    private GenerateHash generateHash;

    @Autowired
    private AuthService authService;
    @Autowired
    private KycService kycService;

    @Autowired
    private CertificateService certService;

    @Autowired
    private SsnExtractionService ssnExtractionService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            User registeredUser = authService.registerUser(userDto);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during registration: " + e.getMessage());
        }
    }

    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        Optional<User> isAuthenticated = authService.loginUser(loginDto);
        if (isAuthenticated != null) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitKycDetails(
            @ModelAttribute KycDetailsDto kycDetailsRequest) throws Exception { 
        kycService.processKycDetails(kycDetailsRequest);
        return ResponseEntity.ok("KYC details submitted successfully.");
    }

    @PostMapping(value = "/processSSN", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SSNResponse> handleSSN(@RequestParam("document") MultipartFile ssn)
            throws IOException, InterruptedException, NoSuchAlgorithmException {
    
        SSNResponse message = ssnExtractionService.loadFile(ssn);  

        return ResponseEntity.ok(message);
    }

    
}