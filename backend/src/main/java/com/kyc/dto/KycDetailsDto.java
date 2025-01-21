package com.kyc.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class KycDetailsDto {
    private String fullName;
    private String email;
    private String dateOfBirth;
    private GovernmentIssuedIdDto governmentIssuedId;
    private String ssnNo;
    private MultipartFile ssnDoc;
    private main.java.com.kyc.dto.ProofOfAddressDto proofOfAddressDto;
    private EmploymentInfoDto employmentInfoDto;
}
