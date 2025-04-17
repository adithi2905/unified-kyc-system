package com.kyc.service;

import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyc.dto.SSNResponse;
import com.kyc.dto.VCResponse;
import com.kyc.entities.GovernmentIssuedId;

@Service
public class SsnExtractionService {

    private final GovernmentIssuedId governmentIssuedId = new GovernmentIssuedId();
    @Autowired 
    private GenerateHash generateHash;
    @Autowired
    private CertificateService certService;
   
    public SSNResponse loadFile(MultipartFile ssn) throws InterruptedException, NoSuchAlgorithmException {
        File tempFile = null;

        try {
            byte[]ssnBytes=ssn.getBytes();
            String extension = Optional.ofNullable(ssn.getOriginalFilename())
                    .filter(f -> f.contains("."))
                    .map(f -> f.substring(f.lastIndexOf(".")))
                    .orElse(".jpg");

            tempFile = File.createTempFile("upload-", extension);
            ssn.transferTo(tempFile);

            String extractedSSN = runExtractionScript(tempFile.getAbsolutePath());

            if (!"NOT_FOUND".equals(extractedSSN))
        {
            governmentIssuedId.setSsn(extractedSSN);
            String hash=generateHash.generateSSNHash(ssnBytes);
            VCResponse vcResponse=certService.generateCertificate("Adithi","2wewsaskas",true);
            SSNResponse ssnResponse = new SSNResponse();
            ssnResponse.setSsnHash(hash);
            ssnResponse.setVcResponse(vcResponse);
            return ssnResponse;
            
        }
        return null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        } 
        finally
        {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
        }}
        
    }

    private String runExtractionScript(String filePath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\adith\\innovation\\unified-kyc-system\\backend\\ocr\\ssnextractor.py", filePath);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }
        process.waitFor();

        String extracted = output.toString().trim();
        return extracted.isEmpty() ? "NOT_FOUND" : extracted;
    }

    public String getExtractedSSN() {
        String id = governmentIssuedId.getSsn();
        return id != null ? id : "NOT_FOUND";
    }

}
