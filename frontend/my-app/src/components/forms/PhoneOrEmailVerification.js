import React, {useEffect,useState} from "react";
import { useLocation } from "react-router-dom";
import {auth,RecaptchaVerifier, signInWithPhoneNumber} from "./firebase-config.js";

const PhoneOrEmailVerification = () =>{
    const location = useLocation();
    const formData = location.state || {};
    const [otp,setOtp]=useState("");
    const [confirmationResult, setConfirmationResult]=useState(null);
    const [verificationResult,setVerificationResult]=useState(null);
    useEffect(() =>  {
        const sendOTP= async()=>
        {
            try{
                if (!auth) {
                    console.error("Firebase Auth is not initialized!");
                    return;}
                if (!window.recaptchaVerifier) {
                    window.recaptchaVerifier = new RecaptchaVerifier("recaptcha-container", {
                        size: "invisible",
                        callback: (response) => {
                            console.log("reCAPTCHA verified!");
                        },
                        "expired-callback": () => {
                            console.log("reCAPTCHA expired. Please refresh.");
                        },
                    },auth);
                await window.recaptchaVerifier.render();
                }
                
            const confirmation=await signInWithPhoneNumber(auth,formData.contact,window.recaptchaVerifier);
            setConfirmationResult(confirmation);
            alert("OTP sent successfully");
            }
            catch(error)
            {
                console.error("Error sending OTP", error);
                alert(`Failed to send OTP: ${error.message}`);
            }
        }
        sendOTP();
        },[formData.contact]);

        const verifyOTP= async() =>
        {
            try{
            if(!otp)
            {
                alert("Pls enter the otp");
                return;
            }
            if(confirmationResult)
            {
            await confirmationResult.confirm(otp);
            alert("Phone number verified successfully");
            
            }
            }
            catch(error)
            {
                console.log(error);
                alert(error.message);
            }
        };
    return (<div><div id="recaptcha-container"></div>
        <label htmlFor="otp">Enter the OTP sent</label>
        <input type="text" name="otp" id="otp" value={otp} onChange={(e)=>setOtp(e.target.value)}/>
        <button onClick={verifyOTP}>Submit</button>
        </div>

    );

};
export default PhoneOrEmailVerification;

