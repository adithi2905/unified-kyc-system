
contract EthereumLayer is CCIPReceiver{
    event MessageReceived(address indexed sender,string message);
    event storeData(address sender,string userDataHash);
    constructor(address router) CCIPReceiver(router){}
    struct User{
        string userDataHash;
        string uid;
    }

    function _ccipReceive(Client.Any2EVMMessage memory message) internal override
    {
        string memory decodedMessage=abi.decode(message.data,(string));
        emit MessageReceived(address(abi.decode(message.sender,(address))),decodedMessage);
    }
    function storeData(address _sender, string _userDataHash) external
    {
        this.userDataHash= _userDataHash;
        this.sender=_sender;
        emit UserDataStored(_sender,_userDataHash);
    }
}