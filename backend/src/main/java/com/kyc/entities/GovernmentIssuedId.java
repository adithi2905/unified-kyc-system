package com.kyc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
public class GovernmentIssuedId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "govt_id")// Primary key generation strategy
    private Long Govtid;


    private String ssn;

    @Transient // This won't persist in the database
    private MultipartFile ssnDoc;

    private String driversLicense;
    @Transient
    private MultipartFile driversLicenseDoc;

    private String passportId;
    @Transient
    private MultipartFile passportDoc;

    private String stateId;
    @Transient
    private MultipartFile stateIdDoc;

    private String militaryId;
    @Transient
    private MultipartFile militaryDoc;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId", unique = true)
    private User user;
}
