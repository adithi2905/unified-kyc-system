pragma solidity^0.8.0

contract KYCContract is RegisterUser{

    struct extendedUser
    {
        bool verified=false;
        bool verificationFailed=false;
    }

    modify onlyUser()
    {
        require(users[msg.sender],"User is not registered");
    }

    modify onlyOrganization()
    {
        require(organizations[msg.sender],"Unauthorized access");
    }
    
    mapping(address=>bool)organizations;

    event OrganizationInitiatedKYCRequest(address user);
    event UserRequestedKYCRequest(address user);
    event UserRequestRejected(address user);
    event UserRequestApproved(address user);
    
    function initiateKYCRequest(address _user) external onlyOrganization
    {
        emit OrganizationInitiatedKYCRequest(_user);
    }

    function requestKYCRequest() external onlyUser
    {
        emit UserRequestedKYCRequest(msg.sender);
    }

    function ApproveKYCRequest(address _user) external onlyInstitution
    {
        require(user[_user].verificationFailed,"User verification is successful");
        emit UserRequestRejected(_user);
        emit UserRequestApproved(_user);
    }
   
 }

