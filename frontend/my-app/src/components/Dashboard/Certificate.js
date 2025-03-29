import React, {useEffect,useState} from "react";

const Certificate = () =>
{
    const[certData,setCertData]=useState(null);
    useEffect(()=>{fetch("Certificate.json").then(res=>res.json()).then(data=>setCertData(data)).catch((err)=>console.error("Error loading JSON",err))},[]);
    return (<div><p>{certData?.name}</p>
    <p>{certData?.ssnNo}</p>
    <p>{certData?.dob}</p>
    <p>{certData?.verifiedBy}</p>
    <p>{certData?.verifiedOn}</p>
    </div>);
}
export default Certificate;