package com.kyc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyc.entities.GovernmentIssuedId;
import com.kyc.entities.KycDetails;

@Service
public class SsnExtractionService {

    private final GovernmentIssuedId governmentIssuedId = new GovernmentIssuedId(); 
    public String loadFile(MultipartFile ssn) throws InterruptedException {
        File tempFile = null;

        try {
            String extension = Optional.ofNullable(ssn.getOriginalFilename())
                    .filter(f -> f.contains("."))
                    .map(f -> f.substring(f.lastIndexOf(".")))
                    .orElse(".jpg");

            tempFile = File.createTempFile("upload-", extension);
            ssn.transferTo(tempFile);

            String extractedSSN = runExtractionScript(tempFile.getAbsolutePath());

            governmentIssuedId.setSsn(extractedSSN);

            return extractedSSN;

        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
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
