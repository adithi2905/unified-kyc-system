package com.kyc.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VerifiableCredentialsDto {
    private String name;
    private String did;
    private String ssnVerificationStatus;
    private String verifiedOn;
    private String verifiedBy = "CREDIFY";
    private String expiresOn;

    public VerifiableCredentialsDto(String name, String did, boolean ssnVerified) {
        this.name = name;
        this.did = did;
        this.ssnVerificationStatus = ssnVerified ? "SSN is verified successfully" : "SSN verification failed";
        this.verifiedOn = LocalDateTime.now().toString();
        this.expiresOn = LocalDateTime.now().plusMonths(6).toString(); 
    }
}
