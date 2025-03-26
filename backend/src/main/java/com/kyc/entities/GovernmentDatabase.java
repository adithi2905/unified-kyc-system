package com.kyc.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GovernmentDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "govt_db_id")// Primary key generation strategy
    private Long GovtDbId;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private String ssn;
    private String driverLicense;
    private String stateId;
    private String militaryId;
    private String passportId;

}
