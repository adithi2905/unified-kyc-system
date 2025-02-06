

pragma solidity ^0.8.19;

//Register user
contract RegisterUser{
    struct User{
        string userDataHash;
        bool isRegistered;
        address registeredBy;

    }
    mapping(address=> User) public users;
    address public admin;
    mapping(address => bool) public institutions;

    modifier onlyAdmin()
    {
        require(msg.sender==admin,"Only admins can perform this action");
        _;
    }

    modifier onlyInstitution()
    {
        require(institutions[msg.sender],"Only approved institution can perform this action");
        _;
    }
    
    constructor()
    {
        admin = msg.sender;
    }

    event UserRegistered(address indexed user,string userDataHash,address indexed registeredBy);
    event UserUpdated(address indexed registeredBy, string newHashedUserData);
    event institutionAdded(address indexed institution);
    event institutionRemoved(address indexed institution);

    function registerUser(address _user,string calldata _userDataHash) external onlyInstitution
    {
        require(!users[_user].isRegistered,"User is already registered");
        users[_user]=User({userDataHash:_userDataHash,isRegistered:true,registeredBy:msg.sender});
        emit UserRegistered(_user,_userDataHash,msg.sender);

    }

    function updateUser(string calldata _newHashedData) external onlyInstitution
    {
        require(users[msg.sender].isRegistered,"User is not registered");
        users[msg.sender].userDataHash=_newHashedData;
        emit UserUpdated(msg.sender,_newHashedData);
    }
    //Get user has to be implemented
    function addInstitution(address _institution) external onlyAdmin
    {
        institutions[_institution]=true;
        emit institutionAdded(_institution);
    }

    function removeInstitution(address _institution) external onlyAdmin
    {
        institutions[_institution]=false;
        emit institutionRemoved(_institution);
    }
     
    }
