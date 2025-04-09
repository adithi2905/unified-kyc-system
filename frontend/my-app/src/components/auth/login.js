import React ,{useState} from React;

const Login=()=>{
    const [email,setEmail]=useState('');
    const [password,setPassword]=useState('');

    const handleLogin = async() =>{
        

    };

    return (
        <div classname='login-container'>
            <h2>Login</h2>
            <input type="email" placeholder="email" value={email} onChange={(e)=>setEmail(e.target.value)}/>
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <button onClick={handleLogin}>Login</button>
    </div>
    );

};
export default Login;