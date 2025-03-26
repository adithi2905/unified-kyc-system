import { useNavigate } from "react-router-dom";
import React, {useState} from "react";
import './forms.css'
const UserRegistration= ()=>
        {
            const[formData,setFormData]= useState({
                name:"",
                username:"",
                email:"",
                countryCode:"",
                contact:"",
                password:""
            });

            const[errors,setErrors]=useState({});
            const [submit,setIsSubmit]=useState(false);
            const navigate=useNavigate();
            const[validatePass,setPasswordValidation]=useState({
                length: false,
                uppercase: false,
                lowercase: false,
                number: false,
                specialChar: false,
            });

            const validatePassword = (value)=>
            {
                if(!value)
                    return false;
                const passValid={
                    length: value.length>=8,
                    uppercase: /[A-z]/.test(value),
                    lowercase: /[a-z]/.test(value),
                    number: /[0-9]/.test(value),
                    specialChar: /[!@#$%^&*(),.?":{}|<>]/.test(value)
                };
            setPasswordValidation(passValid);
            if(passValid.length && passValid.uppercase && passValid.lowercase && passValid.number && passValid.specialChar)
                return true;
            return false;
                //return Object.values(passValid).every(Boolean);
                
            };
            const validate=(values)=>
            {
                let newErrors={};
                const nameRegex=/^[A-Za-z0-9]*$/i;
                const contactRegex=/^\+?\d{0,2}\s?\(?\d{3}\)?[-.\s]?\d{3}[-.\s]?\d{4}$/i;
                if(!nameRegex.test(values.username))
                    newErrors.name="Invalid username";
                if(!contactRegex.test(values.contact))
                    newErrors.contact="Contact number is invalid";
                const isPasswordValid=validatePassword(values.password);
                if(!isPasswordValid)
                    newErrors.password="Your password doesn't meet the requirements";
                return newErrors;
            }
    
            const handleChange = (e) =>{
                setFormData({... formData,[e.target.name]: e.target.value});
                if(e.target.name==="password")
                    validatePassword(e.target.value);
                };

                const handleSubmit = (e) => {
                    e.preventDefault();
                    const validationErrors=validate(formData);
                    setErrors(validationErrors);
                    setIsSubmit(Object.keys(validationErrors).length === 0);
                    console.log("Form submitted:", formData, validationErrors); 
                    if(submit)
                    {
                        navigate("/otp",{state: formData });   
                    }
                    };
                return (
                    <form onSubmit={handleSubmit}>
                        <pre>{JSON.stringify(formData,"","","","")}</pre>
                    <br/><br/><label htmlFor="name" >Let's start with your name. Type it in!:<br/>
                    <input type="text" id="name" name="name" placeholder="name" value={formData.name} onChange={handleChange} required/>
                    </label>
                    <br/><br/><label htmlFor="username" >Let's proceed with username. Type it in!:<br/>
                    <input type="text" id="username" name="username" placeholder="username" value={formData.username} onChange={handleChange} required/>
                    </label>
                    
                    <br/><br/><label htmlFor="email" >Your email, please. We’ll keep it secure!<br/>
                    <input type="email" id="email" name="email" placeholder="email" value={formData.email} onChange={handleChange} required/>
                    </label>
                    <br/><br/><label htmlFor="contact" >To verify your identity, enter your valid phone number<br/>
                    <select name="countryCode" id="countryCode" value={formData.countryCode} onChange={handleChange}>
                        <option value="+1">+1 (USA)</option>
                    </select>
                    <input type="tel" id="contact" name="contact" placeholder="contact" value={formData.contact} onChange={handleChange} required/>
                    </label>
                    <div>
                    <p>Password must contain:</p>
                    <span style={{color: validatePass.length? "green":"red"}}>{validatePass.length ? "✅" : "❌"} Length must be greater than 8</span><br></br>
                    <span style={{color: validatePass.uppercase? "green":"red"}}>{validatePass.uppercase ? "✅" : "❌"} Should have at least one uppercase character</span><br></br>
                    <span style={{color: validatePass.lowercase? "green":"red"}}>{validatePass.lowercase ? "✅" : "❌"} Should have at least one lowercase character </span><br/>
                    <span style={{color: validatePass.number? "green":"red"}}>{validatePass.number ? "✅" : "❌"} Should have at least one number </span><br/>
                    <span style={{color: validatePass.specialChar? "green":"red"}}>{validatePass.specialChar ? "✅" : "❌"} Should have at least one special character </span><br/>
                    </div>
                    <br/><br/><label htmlFor="pass" >For your security, please create a strong password.<br/>
                    <input type="password" name="password" placeholder="password" value={formData.password} onChange={handleChange} required/>
                    </label>
                    <button type="submit">Submit</button>
                    </form>
                    
                );
            };
            
export default UserRegistration;