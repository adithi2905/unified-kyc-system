//package com.kyc.service;
//
//import org.springframework.stereotype.Service;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.http.HttpService;
//import org.web3j.crypto.Credentials;
//import org.web3j.tx.gas.ContractGasProvider;
//import org.web3j.tx.gas.DefaultGasProvider;
//import org.web3j.tx.TransactionManager;
//import org.web3j.tx.RawTransactionManager;
//
//import com.kyc.contract.KycSmartContract;
//
//import java.math.BigInteger;
//import java.util.Map;
//
//@Service
//public class BlockchainService {
//
//    private final Web3j web3j;
//    private final Credentials credentials;
//    private final ContractGasProvider gasProvider;
//    private final KycSmartContract kycContract;
//
//    public BlockchainService() {
//        // Initialize Web3j with the Ethereum node
//        this.web3j = Web3j.build(new HttpService("http://127.0.0.1:8545")); // Replace with your Ethereum node address
//
//        // Load Ethereum wallet credentials
//        this.credentials = Credentials.create("YOUR_PRIVATE_KEY"); // Replace with your private key
//
//        // Set default gas provider
//        this.gasProvider = new DefaultGasProvider();
//
//        // Deploy or load the smart contract
//        this.kycContract = loadSmartContract();
//    }
//
//    private KycSmartContract loadSmartContract() {
//        // Use the contract address if already deployed
//        String contractAddress = "YOUR_CONTRACT_ADDRESS"; // Replace with the deployed contract address
//
//        return KycSmartContract.load(contractAddress, web3j, credentials, gasProvider);
//    }
//
//    public String registerUser(String userId, Map<String, String> documentHash) throws Exception {
//        // Register a user by storing their unique ID and metadata hash on the blockchain
//        String transactionHash = kycContract.registerUser(userId, documentHash).send().getTransactionHash();
//        System.out.println("Transaction Hash: " + transactionHash);
//        return transactionHash;
//    }
//
//    public String getMetadataHash(String userId) throws Exception {
//        // Fetch the metadata hash stored on the blockchain for the given user ID
//        return kycContract.getMetadataHash(userId).send();
//    }
//
//    public boolean verifyUser(String userId, String metadataHash) throws Exception {
//        // Verify the metadata hash for the given user ID
//        String storedHash = kycContract.getMetadataHash(userId).send();
//        return metadataHash.equals(storedHash);
//    }
//
//    public boolean storeOnBlockchain(String uniqueId, Map<String, String> documentHashes) {
//        try {
//            // Convert documentHashes to a string format (JSON-like or delimited)
//            StringBuilder hashData = new StringBuilder();
//            for (Map.Entry<String, String> entry : documentHashes.entrySet()) {
//                hashData.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
//            }
//            String hashString = hashData.toString();
//
//            // Interact with the blockchain smart contract
//            YourSmartContract contract = YourSmartContract.load(
//                    "your-smart-contract-address", web3j, credentials, new DefaultGasProvider());
//            contract.storeUserData(uniqueId, hashString).send();
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//}
//
