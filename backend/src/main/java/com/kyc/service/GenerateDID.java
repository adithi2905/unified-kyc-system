package com.kyc.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import com.kyc.dto.DIDResult;

@Service
public class GenerateDID {
    
    public DIDResult generateDidEthr() throws Exception {
        ECKeyPair keyPair = Keys.createEcKeyPair();
        String privateKey = keyPair.getPrivateKey().toString(16);
        String publicAddress = "0x" + Keys.getAddress(keyPair.getPublicKey());

        DIDResult result = new DIDResult();
        result.did = "did:ethr:" + publicAddress;
        result.publicAddress = publicAddress;
        result.privateKey = privateKey;
        return result;
    }
    
}
