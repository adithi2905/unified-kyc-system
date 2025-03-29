package com.kyc.controller;

import com.kyc.dto.KycDetailsDto;
import com.kyc.dto.LoginDto;
import com.kyc.dto.SessionLogin;
import com.kyc.dto.UserDto;
import com.kyc.entities.User;
import com.kyc.service.*;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/kyc/")
@CrossOrigin("https//localhost:3000")
public class UnifiedKycController{

//    @Autowired
//    private SecretKey jwtSecretKey;
//
//    @Autowired
//    private long jwtExpiration;
@Autowired
private CertificateService certService;


    private final AuthenticationManager authenticationManager;

@Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @Autowired
    private KycService kycService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private KycVerficationService kycVerficationService;

    @Autowired
    private UserService userService;

    public UnifiedKycController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        User user=authService.registerUser(userDto);
        if (user.getPassword().isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password is not matching");
        }
        return ResponseEntity.ok ("User registered successfully") ;
    }

    @PostMapping("/login")
    public ResponseEntity<SessionLogin> loginUser(@RequestBody LoginDto loginDto){
        Optional<User> user = authService.loginUser(loginDto);
        if (user.isPresent()) {
            // Generate JWT
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    ));
            String token = tokenService.generateToken(authentication);

            SessionLogin response = new SessionLogin();
            response.setMessage("Login successful");
            response.setData(user.get());
            response.setAccessToken(token);
            return ResponseEntity.ok(response);
        } else {
            SessionLogin response = new SessionLogin();
            response.setMessage("Invalid Credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/submitKyc")
    public ResponseEntity<String> submitKycDetails(
            @ModelAttribute KycDetailsDto kycDetailsRequest) throws Exception {

        //Optional<User> loggedInUser = userService.getUserByEmail(principal.getName());
        kycService.processKycDetails(kycDetailsRequest);
        return ResponseEntity.ok("KYC details submitted successfully.");
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            if (file == null || file.isEmpty()) {
                System.out.println("File is null or empty");
                throw new IllegalArgumentException("File must not be null or empty");
            }

            String cid = fileStorageService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully. CID: " + cid);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @PostMapping("/retriveFile/{cid}")
    public ResponseEntity<byte[]> retriveFile(@PathVariable String cid){
        try {
            byte[] fileData = fileStorageService.retrieveFile(cid);
            return ResponseEntity.ok(fileData);
        } catch (IOException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

//    @PostMapping("/verfiyKyc")
//    public ResponseEntity<String> kycVerification(@RequestBody KycDetailsDto kycDetailsDto){
//        boolean kycVerficationService.
//    }
@PostMapping("/generateCertificate")
    public ResponseEntity<String> generateCert(@RequestParam String name, @RequestParam String dob, @RequestParam String ssnNo)
    {
        certService.generateCertificate(name, dob, ssnNo);
        return ResponseEntity.ok("Certificate generated");
    }
    

}