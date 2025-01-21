package main.java.com.kyc.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProofOfAddressDto {
    private String propertyOwnership;
    private String utilityBillType;
    private MultipartFile utilityDoc;
    private MultipartFile leaseAgreement;
}