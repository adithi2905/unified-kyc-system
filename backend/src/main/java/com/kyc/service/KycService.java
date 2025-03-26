package com.kyc.service;


import com.kyc.dto.KycDetailsDto;
import com.kyc.entities.*;
import com.kyc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.kyc.utilities.securityConfig.*;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class KycService {

    @Autowired
    private KycRepository kycRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmplymentInfoRepository emplymentInfoRepository;

    @Autowired
    private FileMetaDataRepository fileMetaDataRepository;

    @Autowired
    private GovtIssueIdRepository govtIssueIdRepository;

    @Autowired
    private ProofOfAddressRepository proofOfAddressRepository;

    @Autowired
    private KycVerficationService kycVerficationService;

    public void processKycDetails(KycDetailsDto request) throws Exception {

        //Set User
        // Retrieve logged-in user
        Principal principal = null;
        String userEmail = principal.getName(); // Fetch email from Principal
        Optional<User> loggedInUserOpt = userRepository.findByEmail(userEmail);

        if (loggedInUserOpt.isEmpty()) {
            throw new Exception("User not found with email: " + userEmail);
        }

        User loggedInUser = loggedInUserOpt.get();
        request.getGovernmentIssuedId().setUser(loggedInUser);
        request.getEmploymentInfo().setUser(loggedInUser);
        request.getProofOfAddress().setUser(loggedInUser);

        //verify and update KycDetails
        if(kycVerficationService.verifyKycDetails(request.getGovernmentIssuedId())) {

            Map<String, String> documentHashes = new HashMap<>();

            //Set File Uploads - GovernmentIssueId
            String ssnCid = fileStorageService.uploadFile(request.getGovernmentIssuedId().getSsnDoc());
            storeFileMetaData("SSN", loggedInUser);
            documentHashes.put("ssn",ssnCid);

            String driversLicenceCid = fileStorageService.uploadFile(request.getGovernmentIssuedId().getDriversLicenseDoc());
            storeFileMetaData("DRIVERLICENSE", loggedInUser);
            documentHashes.put("driversLicenceId",driversLicenceCid);

            String militaryCid = fileStorageService.uploadFile(request.getGovernmentIssuedId().getMilitaryDoc());
            storeFileMetaData("MILITARYID", loggedInUser);
            documentHashes.put("militaryId",militaryCid);

            String stateCid = fileStorageService.uploadFile(request.getGovernmentIssuedId().getStateIdDoc());
            storeFileMetaData("STATEID", loggedInUser);
            documentHashes.put("stateId",stateCid);

            String passportCid = fileStorageService.uploadFile(request.getGovernmentIssuedId().getPassportDoc());
            storeFileMetaData("PASSPORT", loggedInUser);
            documentHashes.put("passportId",passportCid);

            //Set File Uploads - EmploymentInfo
            String empProofCid = fileStorageService.uploadFile(request.getEmploymentInfo().getEmpProofDoc());
            storeFileMetaData("EMPPROOF", loggedInUser);
            documentHashes.put("empProofId",empProofCid);

            //Set File Uploads - Proof of Address
            String ProofOfAddCid = fileStorageService.uploadFile(request.getProofOfAddress().getProofOfAddDoc());
            storeFileMetaData(request.getProofOfAddress().getDocName(), loggedInUser);
            documentHashes.put("ProofOfAdd",ProofOfAddCid);

            // save in repos
            emplymentInfoRepository.save(request.getEmploymentInfo());
            govtIssueIdRepository.save(request.getGovernmentIssuedId());
            proofOfAddressRepository.save(request.getProofOfAddress());
        }

        //call blockChain Service
        //BlockchainRegisterUser
        //BlockchainStoreonBlockChain

    }

    public void storeFileMetaData(String fileName, User loggedInUser){
        FileMetaData fileInfo= new FileMetaData();

        fileInfo.setFileName(fileName);
        fileInfo.setUser(loggedInUser);
        fileInfo.setOwnerId(loggedInUser.getUserId());
        fileInfo.setUploadTimestamp(LocalDateTime.now());

        fileMetaDataRepository.save(fileInfo);
    }




}
