import React ,{useState} from "react";
import { useNavigate } from "react-router-dom";


const Login=()=>{
    const [loginData,setLoginData]=useState({
        email:"",
        password:""
    });

    const handleLogin = async() =>{
        console.log("Login attempt with email:", loginData);
        await fetch("http://localhost:8081/login",{method:"POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(loginData)
        });
    };

    const handleChange=(e)=>{
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
    }

    return (
        <div className='login-container'>
            <h2>Login</h2>
            <input type="email" name="email" placeholder="email" value={loginData.email} onChange={handleChange}/>
            <input type="password" name="password" placeholder="Password" value={loginData.password} onChange={handleChange}/>
            <button onClick={handleLogin}>Login</button>
    </div>
    );

};
export default Login;