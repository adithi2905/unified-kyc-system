package com.kyc.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Certificate
{
    @JsonProperty("name")
    private String name;

    @JsonProperty("ssnNo")
    private String ssnNo;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("verifiedBy")
    private String verifiedBy;

    @JsonProperty("issuedAt")
    private String issuedAt;

    public Certificate(String name, String ssnNo, String dob) {
        //TODO Auto-generated constructor stub
        this.name=name;
        this.ssnNo=ssnNo;
        this.dob=dob;
        this.verifiedBy="Credify";
        this.issuedAt=Instant.now().toString();

    }
    

}