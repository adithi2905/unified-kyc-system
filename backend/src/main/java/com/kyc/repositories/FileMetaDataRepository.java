package com.kyc.repositories;

import com.kyc.entities.FileMetaData;
import com.kyc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetaDataRepository extends JpaRepository<FileMetaData,Long> {
}
