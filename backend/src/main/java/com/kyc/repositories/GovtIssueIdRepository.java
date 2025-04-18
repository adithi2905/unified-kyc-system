package com.kyc.repositories;

import com.kyc.entities.GovernmentIssuedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GovtIssueIdRepository extends JpaRepository<GovernmentIssuedId,Long> {
}
