package com.kyc.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyc.dto.VerifiableCredentialsDto;


import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

@Service
public class GenerateHash {

    @SuppressWarnings("deprecation")
    public String generateVCHash(VerifiableCredentialsDto verifiableCredentials) throws IOException, NoSuchAlgorithmException
    {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
        String canonicalJson = objectMapper.writeValueAsString(verifiableCredentials);   
        MessageDigest digest=MessageDigest.getInstance("SHA-256"); 
        byte[] hashBytes=digest.digest(canonicalJson.getBytes(StandardCharsets.UTF_8));
        String hash=Hex.encodeHexString(hashBytes);
        return hash;
    }   

    public String generateSSNHash(byte[]imgBytes) throws IOException, NoSuchAlgorithmException
    {
        MessageDigest digest=MessageDigest.getInstance("SHA-256"); 
        byte[]hashBytes=digest.digest(imgBytes);
        String hash=Hex.encodeHexString(hashBytes);
        return hash;
    }
}
