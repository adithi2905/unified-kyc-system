package com.kyc.dto;

import lombok.Data;

@Data
public class VCResponse
{
    private VerifiableCredentialsDto vc;
    private String vcHash;

    public VCResponse(VerifiableCredentialsDto vc,String vchash)
    {
        this.vc=vc;
        this.vcHash=vchash;

    }
    

}