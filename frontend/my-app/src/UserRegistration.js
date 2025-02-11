import React,{useState} from 'react';

const UserRegistration=()=>{
    const [formData,setFormData]=useState({
        name: '',
        password: '',
        email: '',
        contact: ''

    });

    const handleChange = (e) =>
    {
        const {name,value}=e.target;
        setFormData(prev=> ({...prev,[name]:value}));
    };

    const handleSubmit = (e) =>
    {
        e.preventDefault();
        console.log('Form submitted',formData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                placeholder="Username"
            />
            <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Email"
            />
            <input type="text" name="contact" value={formData.contact} onChange={handleChange} placeholder='Contact'/>
            <input type="password" name="password" value={formData.password} onChange={handleChange} placeholder='Password'/>
            
            <button type="submit">Submit</button>
        </form>
    );

};
export default UserRegistration;