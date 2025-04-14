package com.kyc.dto;

import com.kyc.entities.EmploymentInfo;
import com.kyc.entities.GovernmentIssuedId;
import com.kyc.entities.ProofOfAddress;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KycDetailsDto {

    private GovernmentIssuedId governmentIssuedId;
    private ProofOfAddress proofOfAddress;
    private EmploymentInfo employmentInfo;

}
