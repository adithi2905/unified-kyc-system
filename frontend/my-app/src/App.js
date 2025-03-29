import React from "react";
import UserRegistration from "./components/forms/UserRegistration";
import KYCDocumentCollection from "./components/forms/KYCDocumentCollection";
import PhoneOrEmailVerification from "./components/forms/PhoneOrEmailVerification";
import Certificate from "./components/Dashboard/Certificate";
import {BrowserRouter,Routes,Route} from 'react-router-dom';
function App() {
    
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/cert' element={<Certificate/>}></Route>
                <Route path='/user' element={ <UserRegistration/>}></Route>
                <Route path='/kycdoc' element={<KYCDocumentCollection/>}></Route>
                <Route path="/otp" element={<PhoneOrEmailVerification/>}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App;
