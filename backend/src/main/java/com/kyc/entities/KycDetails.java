package com.kyc.entities;

import com.kyc.dto.EmploymentInfoDto;
import com.kyc.dto.GovernmentIssuedIdDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
public class KycDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kycId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private String fullName;

    private String email;

    private String dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "govtId", referencedColumnName = "govtId")
    private GovernmentIssuedId governmentIssuedId;

    private String ssnNo;

    private String ssnDocPath;

    @Transient // This won't persist in the database
    private MultipartFile ssnDoc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proofOfAddId", referencedColumnName = "proofOfAddId")
    private ProofOfAddress proofOfAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empId", referencedColumnName = "empId")
    private EmploymentInfo employmentInfo;
}
