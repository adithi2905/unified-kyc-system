package com.kyc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class ProofOfAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proofOfAddId;
    private String propertyOwnership;
    private String utilityBillType;
    private String utilityDocPath;
    private String leaseAgreementPath;
}
