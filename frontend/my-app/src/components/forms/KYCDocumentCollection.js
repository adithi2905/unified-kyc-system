import React,{useState,useNavigate} from 'react';
import "./forms.css"

const KYCDocumentCollection = () =>
{
    const[kycDocs,setKycDoc]=useState(null);

    const[submit,setIsSubmit]=useState(false);
    const handleSubmit = async (e)=>
    {
        e.preventDefault();
        setIsSubmit(true);
        const formData=new FormData();
        formData.append("document",kycDocs);
    
        try
        {
        const response= await fetch("http://localhost:8081/api/processSSN", {
            method: "POST",
            body:formData
        });
        const result = await response.text(); 
        console.log("Uploaded successfully:", result);
        }
        catch(err)
        {
            console.error("Upload error:", err);
        }
    }

    const handleChange =(e)=>
    {
        setKycDoc(e.target.files[0]);
    };
    
    return (<form onSubmit={handleSubmit} enctype="multipart/form-data">
        <br/><br/><label for="gid" > Upload your SSN:<br/>
        <input type="file" id="gid" name="document" placeholder="SSN Doc" onChange={handleChange} required/>
        </label>
        <br/><br/><button>Submit</button>
        </form>);

}
export default KYCDocumentCollection;