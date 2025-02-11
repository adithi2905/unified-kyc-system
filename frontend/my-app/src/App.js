import React from "react";
import UserRegistration from "./components/forms/UserRegistration";

function App() {
    // Function to handle form submission
    const handleUserSubmit = (formData) => {
        console.log("User registered:", formData);
        // You can add further logic here (e.g., API calls)
    };

    return (
        <div>
            <h1>User Registration</h1>
            <UserRegistration onSubmit={handleUserSubmit} />
        </div>
    );
}

export default App;
