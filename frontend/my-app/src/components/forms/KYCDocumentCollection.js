import React,{useState} from 'react';

const KYCDocumentCollection = () =>
{
    const[kycDocs,createKycDoc]=useState({
        "user": "",
        "fullName": "",
        "email": "",
        "dateOfBirth": "",
        "govtIssuedId": "",
        "ssnNo": "",
        "ssnDocPath": "",
        "ssnDocument": null
    });

    const[submit,setIsSubmit]=useState(false);

    const handleSubmit = (e)=>
    {
        e.preventDefault();
        setIsSubmit(true);
    }
    const handleChange =(e)=>
    {
        createKycDoc({...kycDocs,[e.target.name]:e.target.value});
    };
    return (<form>
        <input type="text" name="fullName" value={kycDocs.fullName} placeholder='fullName' onChange={handleChange} required/>
        <input type="text" name="email" placeholder='Email' value={kycDocs.email} onChange={handleChange} required/>
        <input type="dateofBirth" name="dateofBirth" value={kycDocs.dateOfBirth} placeholder='dateofBirth' onChange={handleChange} required/>
        <input type="govtIssuedId" name="govtIssuedId" value={kycDocs.govtIssuedId} placeholder="govtIssuedId" onChange={handleChange} required/>
        <input type="text" name="ssnNo" placeholder='ssnNo' value={kycDocs.ssnNo} onChange={handleChange} required/>
        <input type="text" name="ssnDocPath" placeholder='ssnDocPath' value={kycDocs.ssnDocPath} onChange={handleChange} required/>
        <input type="file" name="ssnDocument" placeholder='ssnDocument' value={kycDocs.ssnDocument} onChange={handleChange} required/>
        <button>Submit</button>
        </form>);

}
export default KYCDocumentCollection;