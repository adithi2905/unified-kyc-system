// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@chainlink/contracts/src/v0.8/CCIPSender.sol";

contract PolygonLayer is CCIPSender{
    struct User {
        bool isRegistered;
        bool isVerified;
        string userDataHash;
        uint256 registeredAt;
        uint256 verifiedAt;
    }
    uint64 constant ETHEREUM_CHAIN_ID=11122;//dummy number
    mapping(address => User) public users;
    address public admin;

    event UserRegistered(address indexed user, string userDataHash, uint256 registeredAt);
    event UserUpdated(address indexed user, string newHashedUserData);
    event UserVerified(address indexed user, bool isVerified, uint256 verifiedAt);
    event SendToEthereum(address _user,string _userDataHash);
    event UserVerificationRequested(address indexed _user);

    modifier onlyAdmin() {
        require(msg.sender == admin, "Only admin can verify KYC");
        _;
    }

    constructor(address _router) CCIPSender(_router) //_router will set based on the type of chainlink we use
    {
        admin = msg.sender;  
    }

    function registerUser(address _user, string calldata _userDataHash) public {
        require(!users[_user].isRegistered, "User is already registered");

        users[_user] = User({
            isRegistered: true,
            isVerified: false,
            userDataHash: _userDataHash,
            registeredAt: block.timestamp,
            verifiedAt: 0
        });

        emit UserRegistered(_user, _userDataHash, block.timestamp);
    }

    function verifyKYCData(address _user) external onlyAdmin {
        require(users[_user].isRegistered, "User not registered yet");
        require(!users[_user].isVerified, "User is already verified");

        users[_user].isVerified = true;
        users[_user].verifiedAt = block.timestamp;

        emit UserVerified(_user, true, block.timestamp);
        bytes memory payload=abi.encode(_user,users[_user].userDataHash,block.timestamp);
        sendMessage(ETHEREUM_CHAIN_ID,payload);
        emit SendToEthereum(_user,users[_user].userDataHash);

    }
    function updateUser(address _user,string calldata _newHashedData) external onlyAdmin
    {
        require(users[_user].isRegistered,"User is not registered");
        users[_user].userDataHash=_newHashedData;
        users[_user].registeredAt=block.timestamp;
        emit UserUpdated(_user,_newHashedData);
        emit UserVerificationRequested(_user);
    }
    

    function getKYC(address _user) public view returns (bool, bool, string memory, uint256, uint256) {
        return (
            users[_user].isRegistered,
            users[_user].isVerified,
            users[_user].userDataHash,
            users[_user].registeredAt,
            users[_user].verifiedAt
        );
    }
}
