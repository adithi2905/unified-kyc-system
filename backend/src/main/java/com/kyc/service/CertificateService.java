package com.kyc.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyc.models.Certificate;
import java.io.File;

@Service
public class CertificateService {
        
        public void generateCertificate(String name,String ssn,String dob)
        {
                try
                {
                Certificate cert= new Certificate(name,ssn,dob);
                ObjectMapper mapper=new ObjectMapper();
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("/Users/adith/innoquest/unified-kyc-system/frontend/my-app/Certificate.json"), cert);
                } 
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }   
        }
        

}
