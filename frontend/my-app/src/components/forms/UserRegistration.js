import React, {use, useState} from "react";

const UserRegistration= ()=>
        {
            const[formData,setFormData]= useState({
                name:"",
                email:"",
                contact:"",
                password:""
            });

            const[errors,setErrors]=useState({});
            const [submit,setIsSubmit]=useState(false);

            const validate=(values)=>
            {
                let newErrors={};
                const nameRegex=/^[A-Za-z0-9]*$/i;
                const passwordRegex=/^(?=.*[!@#$%^&*(),.?:{}|<>])[A-Z0-9!@#$%^&*(),.?:{}|<>]+$/i;//Lookahead ?=, it ensures that there is at least one special character
                const contactRegex=/^\+\d{11}$/i;
                if(!nameRegex.test(values.name))
                    errors.name="Invalid username";
                if(values.password<8)
                    errors.values="Password is shorter than 8 in length";
                if(!passwordRegex.test(values.password))
                    errors.values="Password is invalid";
                if(!contactRegex.test(values.contact))
                    errors.contact="Contact number is invalid";

                return errors;
            }
            

            const handleChange = (e) =>{
                setFormData({... formData,[e.target.name]: e.target.value});
                };

                const handleSubmit = (e) => {
                    e.preventDefault();
                    setErrors(validate(formData));
                    setIsSubmit(true);
                    console.log("Form submitted", errors); 
                    console.log("Form submitted", formData); 
                };
                return (
                    <form onSubmit={handleSubmit}>
                        <pre>{JSON.stringify(formData,"","","","")}</pre>
                    <input type="text" name="name" placeholder="name" value={formData.name} onChange={handleChange} required/>
                    <input type="email" name="email" placeholder="email" value={formData.email} onChange={handleChange} required/>
                    <input type="text" name="contact" placeholder="contact" value={formData.contact} onChange={handleChange} required/>
                    <input type="text" name="password" placeholder="password" value={formData.password} onChange={handleChange} required/>
                    <button type="submit">Submit</button>
                    </form>
                    
                );
            };
    
export default UserRegistration;