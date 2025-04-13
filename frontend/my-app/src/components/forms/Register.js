import { useLocation, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";

const Register = () => {
  const location=useLocation();
  const formData = location || {};
  const [result, setResult] = useState("");
  const navigate=useNavigate();
  const registerUser = async () => {
    console.log('Form Data being sent:', JSON.stringify(formData, null, 2));
    
    const userData = {
      name: formData.state?.name,
      email: formData.state?.email,
      contact: formData.state?.contact,
      password: formData.state?.password,
      confirmPassword: formData.state?.confirmPassword
    };
  
    try {
      const response = await fetch('http://localhost:8081/api/register', {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData),
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      const result = await response.text();
      setResult(result);
      alert(result);
    } catch (error) {
      console.error("Registration error:", error);
      setResult("Registration failed: " + error.message);
    }
  };
    useEffect(() => {
    if (location.state) {
      registerUser();
    }
  }, [location.state, navigate]); 

  return (
    <div>
      <h2>Register</h2>
      <p>{result}</p> 
    </div>
  );
};

export default Register;
