import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import './forms.css';

const UserRegistration = () => {
  const [formData, setFormData] = useState({
    name: "",
    username: "",
    email: "",
    contact: "",
    password: "",
    confirmPassword: ""
  });

  const [errors, setErrors] = useState({});
  const [submit, setIsSubmit] = useState(false);
  const navigate = useNavigate();
  const [validatePass, setPasswordValidation] = useState({
    length: false,
    uppercase: false,
    lowercase: false,
    number: false,
    specialChar: false,
  });

  const validatePassword = (value) => {
    if (!value) return false;
    const passValid = {
      length: value.length >= 8,
      uppercase: /[A-z]/.test(value),
      lowercase: /[a-z]/.test(value),
      number: /[0-9]/.test(value),
      specialChar: /[!@#$%^&*(),.?":{}|<>]/.test(value)
    };

    setPasswordValidation(passValid);
    return passValid.length && passValid.uppercase && passValid.lowercase && passValid.number && passValid.specialChar;
  };

  const validate = (values) => {
    let newErrors = {};
    const nameRegex = /^[A-Za-z0-9]*$/i;
    const contactRegex = /^\+?\d{0,2}\s?\(?\d{3}\)?[-.\s]?\d{3}[-.\s]?\d{4}$/i;

    if (!nameRegex.test(values.username)) newErrors.name = "Invalid username";
    if (!contactRegex.test(values.contact)) newErrors.contact = "Contact number is invalid";
    
    const isPasswordValid = validatePassword(values.password);
    if (!isPasswordValid) newErrors.password = "Your password doesn't meet the requirements";

    if (values.password !== values.confirmPassword) {
      newErrors.confirmPassword = "Passwords do not match";
    }

    return newErrors;
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    let vErrors = {};

    if (e.target.name === "password") {
      vErrors = validatePassword(e.target.value);
    }

    if (e.target.name === "confirmPassword" && formData.password !== formData.confirmPassword) {
      vErrors.confirmPassword = "Passwords do not match";
    }
    setErrors(vErrors);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validate(formData);
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length === 0) {
      setIsSubmit(true);
      console.log("Form submitted:", formData);
      navigate("/register", { state: formData });
    } else {
      showAlert(validationErrors);
    }
  };

  const showAlert = (validationErrors) => {
    alert(JSON.stringify(validationErrors, null, 2));
  };

  return (
    <form onSubmit={handleSubmit}>
      <pre>{JSON.stringify(formData, "", "", "", "")}</pre>
      <br /><br />
      <label htmlFor="name">
        Let's start with your name. Type it in!:
        <br />
        <input
          type="text"
          id="name"
          name="name"
          placeholder="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
      </label>
      <br /><br />
      <label htmlFor="username">
        Let's proceed with username. Type it in!:
        <br />
        <input
          type="text"
          id="username"
          name="username"
          placeholder="username"
          value={formData.username}
          onChange={handleChange}
          required
        />
      </label>
      <br /><br />
      <label htmlFor="email">
        Your email, please. We’ll keep it secure!
        <br />
        <input
          type="email"
          id="email"
          name="email"
          placeholder="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
      </label>
      <br /><br />
      <label htmlFor="contact">
        To verify your identity, enter your valid phone number
        <br />
        <input
          type="tel"
          id="contact"
          name="contact"
          placeholder="contact"
          value={formData.contact}
          onChange={handleChange}
          required
        />
      </label>
      <div>
        <p>Password must contain:</p>
        <span style={{ color: validatePass.length ? "green" : "red" }}>
          {validatePass.length ? "✅" : "❌"} Length must be greater than 8
        </span>
        <br />
        <span style={{ color: validatePass.uppercase ? "green" : "red" }}>
          {validatePass.uppercase ? "✅" : "❌"} Should have at least one uppercase character
        </span>
        <br />
        <span style={{ color: validatePass.lowercase ? "green" : "red" }}>
          {validatePass.lowercase ? "✅" : "❌"} Should have at least one lowercase character
        </span>
        <br />
        <span style={{ color: validatePass.number ? "green" : "red" }}>
          {validatePass.number ? "✅" : "❌"} Should have at least one number
        </span>
        <br />
        <span style={{ color: validatePass.specialChar ? "green" : "red" }}>
          {validatePass.specialChar ? "✅" : "❌"} Should have at least one special character
        </span>
        <br />
      </div>
      <br /><br />
      <label htmlFor="pass">
        For your security, please create a strong password.
        <br />
        <input
          type="password"
          name="password"
          placeholder="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="confirmPassword"
          placeholder="confirm password"
          value={formData.confirmPassword}
          onChange={handleChange}
          required
        />
      </label>
      <button type="submit">Submit</button>
    </form>
  );
};

export default UserRegistration;
