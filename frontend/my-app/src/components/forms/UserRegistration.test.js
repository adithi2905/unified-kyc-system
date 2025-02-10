import {render,screen,fireEvent} from "@testing-library/react";
import UserRegistration from "./UserRegistration";
import "@testing-library/jest-dom";


test("renders forms correctly",() =>{
    render(<UserRegistration onSubmit={()=>{}}/>);

    //Checking if the form is correctly populated
    expect(screen.getByPlaceholderText("Name")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Email").toBeInTheDocument());
    expect(screen.getByPlaceholderText("Contact").toBeInTheDocument());
    expect(screen.getByPlaceholderText("password").toBeInTheDocument());
    expect(screen.getByRole("button", { name: /submit/i })).toBeInTheDocument();
});

    test("updates input values when typed",()=>{
        render(<UserRegistration onSubmit={() => {}} />);


        const nameInput = screen.getByPlaceholderText("Name");
        const emailInput = screen.getByPlaceholderText("Email");
        const contactInput = screen.getByPlaceholderText("Contact");
        const passwordInput=screen.getAllByPlaceholderText("password");

        fireEvent.change(nameInput, { target: { value: "John Doe" } });
        fireEvent.change(emailInput, { target: { value: "john@example.com" } });
        fireEvent.change(contactInput, { target: { value: "+19304044549" } });
        fireEvent.change(passwordInput, { target: { value: "+19304044549" } });
        
        expect(nameInput.value).toBe("John Doe");
        expect(emailInput.value).toBe("john@example.com");
        expect(contactInput.value).toBe("+19304044549");
        expect(passwordInput.value).toBe("+19304044549");

        fireEvent.click(submitButton);

        expect(mockSubmit).toHaveBeenCalledWith({
        name: "John Doe",
        email: "john@example.com",
        contact: "+19304044549",
        password: "+19304044549"
});
});





