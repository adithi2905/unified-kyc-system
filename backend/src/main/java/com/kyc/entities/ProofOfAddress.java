package com.kyc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Getter
@Setter
public class ProofOfAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proofOfAddId;
    public String docName;

    @Transient
    private MultipartFile proofOfAddDoc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
