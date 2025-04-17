
import React, {useEffect,useState} from "react";

const Certificate = () =>
{
    const[certData,setCertData]=useState(null);
    useEffect(()=>{fetch("http://localhost:8081/api/generateCertificate").then(res=>res.json()).then(data=>setCertData(data)).catch((err)=>console.error("Error loading JSON",err))},[]);
    return (<div style={{
        border: '2px solid black',
        borderRadius: '110px',
        padding: '16px'
    }}><h1>CERTIFICATE OF VERIFICATION</h1>
    <center><h2>{certData?.name}</h2>
    <h2>is born on {certData?.dob},</h2>
    <h3>Her SSN has been verified successfully</h3>
    <h3>on {certData?.verifiedOn}.</h3>
    <h4>by {certData?.verifiedBy}</h4>
    <p>Valid for next six months.</p></center>
    </div>);
}
export default Certificate;