import React from "react";
import UserRegistration from "./components/forms/UserRegistration";
import {BrowserRouter,Routes,Route} from 'react-router-dom';

function App() {
    
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/user' element={ <UserRegistration/>}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App;
