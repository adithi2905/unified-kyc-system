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

    public boolean verifyKycDetails(GovernmentIssuedId req){

        boolean status= true;
        String fullName= req.getUser().getFullName();
        User govUser = governmentDatabaseRepo.findUserBySsn(req.getSsn());
        if(fullName.equals(govUser.getFullName())){

            if(req.getSsn()!=null && !req.getSsn().isEmpty()){
                status = governmentDatabaseRepo.existsBySsn(req.getSsn());
            }

            String driverLicense = req.getDriversLicense();
            if(driverLicense!=null && !driverLicense.isEmpty()){
                status = status && governmentDatabaseRepo.existsByDriverLicense(driverLicense);
            }

            String stateId = req.getStateId();
            if(stateId!=null && !stateId.isEmpty()){
                status = status && governmentDatabaseRepo.existsByStateId(stateId);
            }

            String militaryId = req.getMilitaryId();
            if(militaryId!=null && !militaryId.isEmpty()){
                status = status && governmentDatabaseRepo.existsByMilitaryId(militaryId);
            }

            String passportId = req.getPassportId();
            if(passportId!=null && !passportId.isEmpty()){
                status = status && governmentDatabaseRepo.existsByPassportId(passportId);
            }
        }else{
            return false;
        }
        return status;

    }
}