import React from "react";
import UserRegistration from "./components/forms/UserRegistration";
import KYCDocumentCollection from "./components/forms/KYCDocumentCollection";
import PhoneOrEmailVerification from "./components/forms/PhoneOrEmailVerification";
import Certificate from "./components/Dashboard/Certificate";
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import Register from "./components/forms/register";
import Login from "./components/auth/login";

function App() {
    
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/cert' element={<Certificate/>}></Route>
                <Route path='/user' element={ <UserRegistration/>}></Route>
                <Route path='/kycdoc' element={<KYCDocumentCollection/>}></Route>
                <Route path="/otp" element={<PhoneOrEmailVerification/>}></Route>
                <Route path="/register" element={<Register/>}></Route>
                <Route path="/login" element={<Login/>}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App;
