package com.kyc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyc.dto.SSNResponse;
import com.kyc.dto.VCResponse;

@Service
public class ProcessSSNResult {
    
    @Autowired 
    private GenerateHash generateHash;
    
    @Autowired
    private CertificateService certService;

    @Autowired
    private GenerateDID generateDID;

    public SSNResponse processSSNResults(String extractedSSN,byte[]ssnBytes) throws Exception
    {
            String hash=generateHash.generateSSNHash(ssnBytes);
            com.kyc.dto.DIDResult DIDResponse=generateDID.generateDidEthr();
            VCResponse vcResponse=certService.generateCertificate("Adithi",DIDResponse.getDid(),true);
            SSNResponse ssnResponse = new SSNResponse();
            ssnResponse.setDidResponse(DIDResponse);
            ssnResponse.setSsnHash(hash);
            ssnResponse.setVcResponse(vcResponse);
            return ssnResponse;
            
    }
}
