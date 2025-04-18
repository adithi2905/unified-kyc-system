package com.kyc.service;
import com.kyc.entities.User;
import com.kyc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a repository for User entities

    /**
     * Method to fetch a user by their email address.
     *
     * @param email The email of the logged-in user.
     * @return User The user object.
     */
    public Optional<User> getUserByEmail(String email) {
        // Find the user by email (you may want to handle the case when the user doesn't exist)
        return userRepository.findByEmail(email);
    }

}

