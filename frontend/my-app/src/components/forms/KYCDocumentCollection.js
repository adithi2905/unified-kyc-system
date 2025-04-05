import React,{useState} from 'react';
import "./forms.css"

const KYCDocumentCollection = () =>
{
    const[kycDocs,createKycDoc]=useState({
        "fullName": "",
        "email": "",
        "dateOfBirth": "",
        "govtIssuedDoc": null,
        "proofOfAddress": null,
        "incomeCertificate": null
    });

    const[submit,setIsSubmit]=useState(false);

    const handleSubmit = (e)=>
    {
        e.preventDefault();
        setIsSubmit(true);
    }
    const validate = (e)=>
    {

    }
    const handleChange =(e)=>
    {
        createKycDoc({...kycDocs,[e.target.name]:e.target.value});
    };
    return (<form action="/submit" method="POST" enctype="multipart/form-data">
        <br/><br/><label for="dob" >Date of Birth:<br/>
        <input type="date" id="dob" name="dateofBirth" value={kycDocs.dateOfBirth} placeholder='dateofBirth' onChange={handleChange} required/>
        </label>
        <br/><br/><label for="gid" >Government Issued Doc:<br/>
        <input type="file" id="gid" name="govtIssuedDoc" value={kycDocs.govtIssuedId} placeholder="govtIssuedDoc" onChange={handleChange} required/>
        </label>
        <br/><br/><label for="poa" >Proof of Address:<br/>
        <input type="file" id="poa" name="proofOfAddress" placeholder='proofOfAddress' value={kycDocs.ssnNo} onChange={handleChange} accept="image/*" required/>
        </label>
        <br/><br/><label for="ic" >Income Certificate: <br/>
        <input type="file" id="ic" name="incomeCertificate" placeholder='incomeCertificate' value={kycDocs.ssnDocPath} onChange={handleChange} required/>
        </label>
        <br/><br/><button>Submit</button>
        </form>);

}
export default KYCDocumentCollection;