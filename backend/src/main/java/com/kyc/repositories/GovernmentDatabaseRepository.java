package com.kyc.repositories;

import com.kyc.entities.GovernmentDatabase;
import com.kyc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GovernmentDatabaseRepository extends JpaRepository<GovernmentDatabase,Long> {

    //To check the user first
    @Query("SELECT g.user FROM GovernmentDatabase g WHERE g.ssn = :ssn")
    User findUserBySsn(String ssn);

    boolean existsBySsn(String ssn);
    boolean existsByDriverLicense(String driverLicense);
    boolean existsByStateId(String stateId);
    boolean existsByMilitaryId(String militaryId);
    boolean existsByPassportId(String passportId);
}
