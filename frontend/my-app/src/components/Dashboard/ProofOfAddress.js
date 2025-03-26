import React,{useState} from "react";

const ProofOfAddress=() =>{
    
    const[formData,setFormData]=useState({
        "proofOfAddressId": null,
        "propertyOwnership": null,
        "utilityBillType": null,
        "utilityDocPath": null,
        "leaseAgreementPath": null
    })

    const handleChange = (e) => {
        setFormData({...formData,[e.targetName]: e.targetValue});
    };

    const handleSubmit=()=>{
        e.preventDefault();
        console.log("Form submitted",formData);
        
    }

    return (<div>
        <input type="text" name="proofOfAddressId" placeholder="proofOfAddressId" value={formData.name} />
        <input type="text" name="propertyOwnership" placeholder="propertyOwnership" value={formData.email} />
        <input type="text" name="utilityBillType" placeholder="utilityBillType" value={formData.contact} />
        <input type="text" name="utilityDocPath" placeholder="utilityDocPath" value={formData.password} />
        <button type="submit"/>
        </div>
        

    );

};