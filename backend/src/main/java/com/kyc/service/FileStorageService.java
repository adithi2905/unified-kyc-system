package com.kyc.service;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

    private static final String IPFS_NODE_ADDRESS = "/ip4/127.0.0.1/tcp/5001";
    private final IPFS ipfs;

    public FileStorageService(){
        this.ipfs = new IPFS(IPFS_NODE_ADDRESS);
    }

    public String uploadFile(MultipartFile file) throws IOException {

        // Convert MultipartFile to NamedStreamable
        NamedStreamable.InputStreamWrapper fileStream =
                new NamedStreamable.InputStreamWrapper(file.getInputStream());

        // Upload to IPFS
        MerkleNode response = ipfs.add(fileStream).get(0);
        return response.hash.toString();
    }

    public byte[] retrieveFile(String cid) throws IOException {
        return ipfs.cat(Multihash.fromBase58(cid));
    }
}
