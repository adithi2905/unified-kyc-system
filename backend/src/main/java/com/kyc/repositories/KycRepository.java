package com.kyc.repositories;

import com.kyc.entities.KycDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<KycDetails,Long> {
}
