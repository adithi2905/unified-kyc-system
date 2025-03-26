package com.kyc.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

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

    private String kycStatus; // PENDING, APPROVED, REJECTED
    private LocalDateTime submittedAt;
    private LocalDateTime approvedAt;
    private UUID reviewedBy; // Admin ID
}
