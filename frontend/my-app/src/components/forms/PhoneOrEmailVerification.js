import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { auth, RecaptchaVerifier, signInWithPhoneNumber } from "./firebase-config.js";
import "./forms.css"

const PhoneOrEmailVerification = () => {
    const location = useLocation();
    const formData = location.state || {};
    const [otp, setOtp] = useState("");
    const [confirmationResult, setConfirmationResult] = useState(null);
    const fullPhoneNumber = `+1${formData.contact}`;
    const navigate=useNavigate();

    useEffect(() => {
        const sendOTP = async () => {
            try {
                
                if (!auth) {
                    console.error("Firebase Auth is not initialized!");
                    return;
                }

                if (!formData.contact) {
                    alert("Phone number is missing!");
                    return;
                }

                if (!window.recaptchaVerifier) {
                    window.recaptchaVerifier = new RecaptchaVerifier(auth, "recaptcha-container", {
                        size: "invisible",
                        callback: () => console.log("reCAPTCHA verified!"),
                        "expired-callback": () => console.log("reCAPTCHA expired. Please refresh."),
                    });
                    await window.recaptchaVerifier.render();
                }
                
                const confirmation = await signInWithPhoneNumber(auth,fullPhoneNumber, window.recaptchaVerifier);
                setConfirmationResult(confirmation);
                alert("OTP sent successfully!");
                navigate("/register", { state: formData });
            } catch (error) {
                console.error("Error sending OTP:", error);
                alert(`Failed to send OTP: ${error.message}`);
            }
        };

        sendOTP();
    }, [fullPhoneNumber]);

    const verifyOTP = async () => {
        try {
            if (!otp) {
                alert("Please enter the OTP.");
                return;
            }

            if (!confirmationResult) {
                alert("OTP verification failed! Please request a new OTP.");
                return;
            }

            await confirmationResult.confirm(otp);
            alert("Phone number verified successfully!");
        } catch (error) {
            console.error("Error verifying OTP:", error);
            alert("Invalid OTP. Please try again.");
        }
    };
    const registerUser=async (e)=>{
        try {
            navigate("/otp",{state: formData });   
            const response = await fetch('http://localhost:8081/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
            });

            const result = await response.text();
            alert(result);
        
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to submit');}
        }; 
        
    return (
        <div>
            <h2>Phone Number Verification</h2>
            <p>OTP has been sent to: {fullPhoneNumber}</p>
            <div id="recaptcha-container"></div>

            <label htmlFor="otp">Enter the OTP sent</label>
            <input 
                type="text"  
                name="otp" 
                id="otp"
                value={otp}  
                maxLength={6}  
                placeholder="Enter OTP"
                onChange={(e) => setOtp(e.target.value)}
            />
            <button onClick={verifyOTP}>Submit</button>
        </div>
    );
};

export default PhoneOrEmailVerification;
