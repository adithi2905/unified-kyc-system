package com.kyc.service;

import org.springframework.stereotype.Service;
import com.kyc.dto.VerifiableCredentialsDto;
@Service
public class CertificateService {
        
        public String generateCertificate(String name,String did,Boolean ssnVerified)
        {
                try
                {
                VerifiableCredentialsDto cert= new VerifiableCredentialsDto(name,did,ssnVerified);
                GenerateHash generateVCHash=new GenerateHash();
                String vchash=generateVCHash.generateVCHash(cert);
                return vchash;
                } 
                catch(Exception ex)
                {
                        ex.printStackTrace();
                        return null;  
                } 
                
        }
        

}
