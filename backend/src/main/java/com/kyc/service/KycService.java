package com.kyc.service;

import com.kyc.dto.EmploymentInfoDto;
import com.kyc.dto.GovernmentIssuedIdDto;
import com.kyc.dto.KycDetailsDto;
import com.kyc.entities.EmploymentInfo;
import com.kyc.entities.GovernmentIssuedId;
import com.kyc.entities.KycDetails;
import com.kyc.entities.ProofOfAddress;
import com.kyc.repositories.KycRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KycService {

    @Autowired
    private KycRepository kycRepo;

    @Autowired
    private FileStorageService fileStorageService;

    public void processKycDetails(KycDetailsDto request){
        KycDetails kycDetails = new KycDetails();
        kycDetails.setFullName(request.getFullName());
        kycDetails.setEmail(request.getEmail());
        kycDetails.setDateOfBirth(request.getDateOfBirth());
        kycDetails.setGovernmentIssuedId(mapGovernmentIssuedId(request.getGovernmentIssuedId()));
        kycDetails.setSsnNo(request.getSsnNo());
        kycDetails.setProofOfAddress(mapProofOfAddress(request.getProofOfAddressDto()));
        kycDetails.setEmploymentInfo(mapEmploymentInfo(request.getEmploymentInfoDto()));

        // Handle file upload
        String filePath = fileStorageService.storeFile(request.getSsnDoc());
        kycDetails.setSsnDocPath(filePath);

        // Save to database
        kycRepo.save(kycDetails);
    }

    private GovernmentIssuedId mapGovernmentIssuedId(GovernmentIssuedIdDto dto) {
        GovernmentIssuedId entity = new GovernmentIssuedId();
        entity.setDriversLicense(dto.getDriversLicense());
        entity.setDriversLicenseDocPath(fileStorageService.storeFile(dto.getDriversLicenseDoc()));
        entity.setPassportId(dto.getPassportId());
        entity.setPassportDocPath(fileStorageService.storeFile(dto.getPassportDoc()));
        entity.setStateId(dto.getStateId());
        entity.setStateIdDocPath(fileStorageService.storeFile(dto.getStateIdDoc()));
        entity.setMilitaryId(dto.getMilitaryId());
        entity.setMilitaryDocPath(fileStorageService.storeFile(dto.getMilitaryDoc()));
        return entity;
    }

    private ProofOfAddress mapProofOfAddress(main.java.com.kyc.dto.ProofOfAddressDto dto) {
        ProofOfAddress entity = new ProofOfAddress();
        entity.setPropertyOwnership(dto.getPropertyOwnership());
        entity.setUtilityBillType(dto.getUtilityBillType());
        entity.setUtilityDocPath(fileStorageService.storeFile(dto.getUtilityDoc()));
        entity.setLeaseAgreementPath(fileStorageService.storeFile(dto.getLeaseAgreement()));
        return entity;
    }

    private EmploymentInfo mapEmploymentInfo(EmploymentInfoDto dto) {
        EmploymentInfo entity = new EmploymentInfo();
        entity.setOccupation(dto.getOccupation());
        entity.setEmployersName(dto.getEmployersName());
        entity.setIncomeCertificatePath(fileStorageService.storeFile(dto.getIncomeCertificate()));
        return entity;
    }

}
