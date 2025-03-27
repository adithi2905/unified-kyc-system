package com.kyc.service;

import com.kyc.entities.GovernmentDatabase;
import com.kyc.entities.GovernmentIssuedId;
import com.kyc.entities.User;
import com.kyc.repositories.GovernmentDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KycVerficationService {

    @Autowired
    private GovernmentDatabaseRepository governmentDatabaseRepo;

    public boolean verifyKycDetails(GovernmentIssuedId req) {

        boolean status = true;
//        String fullName= req.getUser().getFullName();
        //   User govUser = governmentDatabaseRepo.findUserBySsn(req.getSsn());
//        if(req.getSsn()!=null && req.getSsn() == governmentDatabaseRepo.findUserBySsn(req){

        if (req.getSsn() != null && !req.getSsn().isEmpty()) {
            status = governmentDatabaseRepo.existsBySsn(req.getSsn());
            System.out.println("statusSSN" + status);
        }

        String driverLicense = req.getDriversLicense();
        if (driverLicense != null && !driverLicense.isEmpty()) {
            status = status && governmentDatabaseRepo.existsByDriverLicense(driverLicense);
            System.out.println("req driverse License" + driverLicense);
            System.out.println("GovtDriverLicense" + governmentDatabaseRepo.existsByDriverLicense(driverLicense));
            System.out.println("statusdrivers" + status);

        }

        String stateId = req.getStateId();
        if (stateId != null && !stateId.isEmpty()) {
            status = status && governmentDatabaseRepo.existsByStateId(stateId);
            System.out.println("stateID" + status);
        }

        String militaryId = req.getMilitaryId();
        if (militaryId != null && !militaryId.isEmpty()) {
            status = status && governmentDatabaseRepo.existsByMilitaryId(militaryId);
            System.out.println("militaryID" + status);
        }

        String passportId = req.getPassportId();
        if (passportId != null && !passportId.isEmpty()) {
            status = status && governmentDatabaseRepo.existsByPassportId(passportId);
            System.out.println("passportId" + status);
        }
        System.out.println("Status" + status);

        return status;
    }

}

