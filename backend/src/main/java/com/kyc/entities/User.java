package com.kyc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String email;
    private String contact;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EmploymentInfo> employmentInfoList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GovernmentIssuedId governmentIds;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProofOfAddress> proofOfAddresses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private KycDetails kycDetails;
}
