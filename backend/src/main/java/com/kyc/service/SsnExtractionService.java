package com.kyc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.kyc.entities.KycDetails;


@Service
public class SsnExtractionService {

    KycDetails kycDetails=new KycDetails();
    public String loadFile() throws IOException, InterruptedException{
        ProcessBuilder pb = new ProcessBuilder("python", "ocr/ssnextractor.py", "resized.png");
        pb.redirectErrorStream(true);
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line="";
        String ssnNo="";
        while((line=reader.readLine())!=null)
        {
            
        ssnNo=line;
        }
        process.waitFor();
        kycDetails.setSsnNo(ssnNo);
        if(!(ssnNo!=null && (ssnNo.equals(""))))
            return "SSN is extracted successfully";
        return "SSN is not found";
    }

    
}
