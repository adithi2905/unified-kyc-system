import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import UserRegistration from './UserRegistration';

describe('UserRegistration Component', () => {
    // Test setup
    const mockOnSubmit = jest.fn();
    
    beforeEach(() => {
        mockOnSubmit.mockClear();
    });

    // Helper function to fill form
    const fillForm = async (user) => {
        await user.type(screen.getByLabelText(/name/i), 'John Doe');
        await user.type(screen.getByLabelText(/email/i), 'john@example.com');
        await user.type(screen.getByLabelText(/contact/i), '1234567890');
        await user.type(screen.getByLabelText(/password/i), 'password123');
    };

    // Test cases
    test('renders all form fields', () => {
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        expect(screen.getByLabelText(/name/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/contact/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /register/i })).toBeInTheDocument();
    });

    test('validates empty form submission', async () => {
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        fireEvent.click(screen.getByRole('button', { name: /register/i }));
        
        expect(await screen.findByText(/name is required/i)).toBeInTheDocument();
        expect(await screen.findByText(/email is required/i)).toBeInTheDocument();
        expect(await screen.findByText(/contact number is required/i)).toBeInTheDocument();
        expect(await screen.findByText(/password is required/i)).toBeInTheDocument();
        expect(mockOnSubmit).not.toHaveBeenCalled();
    });

    test('validates email format', async () => {
        const user = userEvent.setup();
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        await user.type(screen.getByLabelText(/email/i), 'invalid-email');
        fireEvent.click(screen.getByRole('button', { name: /register/i }));
        
        expect(await screen.findByText(/please enter a valid email/i)).toBeInTheDocument();
        expect(mockOnSubmit).not.toHaveBeenCalled();
    });

    test('validates contact number format', async () => {
        const user = userEvent.setup();
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        await user.type(screen.getByLabelText(/contact/i), '123');
        fireEvent.click(screen.getByRole('button', { name: /register/i }));
        
        expect(await screen.findByText(/please enter a valid 10-digit number/i)).toBeInTheDocument();
        expect(mockOnSubmit).not.toHaveBeenCalled();
    });

    test('validates password length', async () => {
        const user = userEvent.setup();
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        await user.type(screen.getByLabelText(/password/i), 'short');
        fireEvent.click(screen.getByRole('button', { name: /register/i }));
        
        expect(await screen.findByText(/password must be at least 8 characters/i)).toBeInTheDocument();
        expect(mockOnSubmit).not.toHaveBeenCalled();
    });

    test('submits form with valid data', async () => {
        const user = userEvent.setup();
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        await fillForm(user);
        fireEvent.click(screen.getByRole('button', { name: /register/i }));
        
        await waitFor(() => {
            expect(mockOnSubmit).toHaveBeenCalledWith({
                name: 'John Doe',
                email: 'john@example.com',
                contact: '1234567890',
                password: 'password123'
            });
        });
        
        expect(await screen.findByText(/registration successful/i)).toBeInTheDocument();
    });

    test('shows error message when submission fails', async () => {
        const user = userEvent.setup();
        mockOnSubmit.mockRejectedValueOnce(new Error('Submission failed'));
        
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        await fillForm(user);
        fireEvent.click(screen.getByRole('button', { name: /register/i }));
        
        expect(await screen.findByText(/registration failed/i)).toBeInTheDocument();
    });

    test('disables submit button during submission', async () => {
        const user = userEvent.setup();
        render(<UserRegistration onSubmit={mockOnSubmit} />);
        
        await fillForm(user);
        const submitButton = screen.getByRole('button', { name: /register/i });
        
        fireEvent.click(submitButton);
        expect(submitButton).toBeDisabled();
        
        await waitFor(() => {
            expect(submitButton).not.toBeDisabled();
        });
    });
});