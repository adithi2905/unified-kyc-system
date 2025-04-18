package com.kyc.dto;


import lombok.Data;

@Data
public class SSNResponse
{

    private VCResponse vcResponse;
    private String ssnHash;
    private DIDResult DidResponse;
    public SSNResponse()
    {}
}