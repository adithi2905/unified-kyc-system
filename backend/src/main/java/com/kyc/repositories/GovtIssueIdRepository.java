package com.kyc.repositories;

import com.kyc.entities.GovernmentIssuedId;
import com.kyc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GovtIssueIdRepository extends JpaRepository<GovernmentIssuedId,Long> {
}
