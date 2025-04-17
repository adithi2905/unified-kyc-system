package com.kyc.service;

import org.springframework.stereotype.Service;

import com.kyc.dto.VCResponse;
import com.kyc.dto.VerifiableCredentialsDto;
@Service
public class CertificateService {
        
        public VCResponse generateCertificate(String name,String did,Boolean ssnVerified)
        {
                try
                {
                VerifiableCredentialsDto cert= new VerifiableCredentialsDto(name,did,ssnVerified);
                GenerateHash generateVCHash=new GenerateHash();
                String vchash=generateVCHash.generateVCHash(cert);
                VCResponse vcResponse=new VCResponse(cert, vchash);
                return vcResponse; 
        } 
                catch(Exception ex)
                {
                        ex.printStackTrace();
                        return null;
                } 
                
        }
        

}
