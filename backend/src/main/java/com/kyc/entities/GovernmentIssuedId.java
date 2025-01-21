package com.kyc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
public class GovernmentIssuedId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long govtId;
    private String driversLicense;
    private String driversLicenseDocPath;
    private String passportId;
    private String passportDocPath;
    private String stateId;
    private String stateIdDocPath;
    private String militaryId;
    private String militaryDocPath;
}
