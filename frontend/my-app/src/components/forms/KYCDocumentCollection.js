import React,{useState,useNavigate} from 'react';
import "./forms.css"

const KYCDocumentCollection = () =>
{
    const[kycDocs,createKycDoc]=useState({
        "govtIssuedDoc": null
    });

    const[submit,setIsSubmit]=useState(false);

    const handleSubmit = async (e)=>
    {
        e.preventDefault();
        setIsSubmit(true);
        try
        {
        const response= await fetch("http://localhost:8081/processSSN", kycDocs,{
            headers: {
                "Content-Type": "multipart/form-data"}
        });
        console.log("Uploaded successfully:", response.data);
        }
        catch(err)
        {
            console.error("Upload error:", err);
        }
    }

    const handleChange =(e)=>
    {
        createKycDoc({...kycDocs,[e.target.name]:e.target.value});
        console.log(kycDocs);
    };
    
    return (<form action="/submit" method="POST" enctype="multipart/form-data">
        <br/><br/><label for="gid" > Upload your SSN:<br/>
        <input type="file" id="gid" name="govtIssuedDoc" value={kycDocs.govtIssuedId} placeholder="govtIssuedDoc" onChange={handleChange} required/>
        </label>
        <br/><br/><button onClick={handleSubmit}>Submit</button>
        </form>);

}
export default KYCDocumentCollection;