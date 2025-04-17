package com.kyc.dto;

import lombok.Data;

@Data
public class SSNResponse
{

    private VCResponse vcResponse;
    private String ssnHash;
    public SSNResponse()
    {}
    public SSNResponse(VCResponse vcResponse,String ssnHash)
    {
        this.vcResponse=vcResponse;
        this.ssnHash=ssnHash;
    }

}