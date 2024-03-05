package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.User;
import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;
import com.prajnafoundation.volunteerdonorportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseObj getAllUsers() {
        List<User> users = userRepository.findAll();
        String logMessage;
        if(users.isEmpty()){
            logMessage = "No Users exist!";
            return new UserResponseObj(logMessage);
        }
        logMessage = "Successfully found "+ users.size()+ " user(s)! " + "To search for a User please provide Id or Email";
        return new UserResponseObj(users, logMessage);
    }


    @Override
    public UserResponseObj getUserById(Long id) {
        String logMessage;
        if (id == null){
            logMessage = "No user exist with id " + id +"!";
            return new UserResponseObj(logMessage);
        }
        Optional<User> userOptional = userRepository.findById(id);

        List<User> user = userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);

        if(user.isEmpty()){
            logMessage = "No user exist with id " + id +"!";
            return new UserResponseObj(logMessage);
        }
        logMessage = "Successfully found "+ user.size()+ " user with Id: "+ id;
        return new UserResponseObj(user, logMessage);
    }

    @Override
    public UserResponseObj getUserByEmail(String email) {
        String logMessage;
        if (email == null){
            logMessage = "No user exist with email " + email +"!";
            return new UserResponseObj(logMessage);
        }
        Optional<User> userOptional = findUserbyEmail(email);

        List<User> user = userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);

        if (user.isEmpty()) {
            logMessage = "No user exist with email " + email + "!";
            return new UserResponseObj(logMessage);
        }
        logMessage = "Successfully found " + user.size() + " user with Email: "+ email;
        return new UserResponseObj(user, logMessage);
    }

    @Override
    public UserResponseObj authenticateUser(String email, String password) {
        Optional<User> userOptional = findUserbyEmail(email);

        if (userOptional.isEmpty()) {
            String logMessage = "Incorrect Credentials: Email";
            return new UserResponseObj(logMessage);
        }

        // User with the given email exists, now check the password
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            String logMessage = "Incorrect Credentials: Password";
            return new UserResponseObj(logMessage);
        }

        // Both email and password are correct
        String logMessage = "Success!";
        return new UserResponseObj(logMessage);
    }

    @Override
    @Transactional
    public UserResponseObj createUser(String email, String password, String phoneNumber, String role,
                             String name, boolean emailNotification, boolean whatsappNotification) {
        // Check if a user with the given email already exists
        Optional<User> existingUser = findUserbyEmail(email);
        String logMessage;
        if (existingUser.isPresent()) {
            logMessage = "User with email " + email + " already exists. Profile creation denied!";
            return new UserResponseObj(logMessage);
        }

        // Create a new user profile
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setName(name);
        user.setEmailNotification(emailNotification);
        user.setWhatsappNotification(whatsappNotification);
        userRepository.save(user);

        logMessage = "User profile created for " + name;
        return new UserResponseObj(logMessage);
    }


    @Override
    @Transactional
    public UserResponseObj updateUser(Long id, String email, String password, String phoneNumber, String role,
                             String name, Boolean emailNotification, Boolean whatsappNotification) {
        Optional<User> userOptional = userRepository.findById(id);
        StringBuilder logMessage;
        if (userOptional.isEmpty()) {
            logMessage = new StringBuilder("User with ID " + id + " not found.");
        }
        else {
            User user = userOptional.get();
            logMessage = new StringBuilder("User with ID " + id + " and name " + user.getName() + " has been updated. Updated fields: ");

            if (email != null) {
                // Check if a user with the given email already exists
                Optional<User> existingUser = findUserbyEmail(email);
                if (existingUser.isPresent() && !Objects.equals(existingUser.get().getUserId(), userOptional.get().getUserId())) {
                    logMessage = new StringBuilder("Cannot update user with email " + email + " as it already exists for another user.");
                    return new UserResponseObj(logMessage.toString());
                }

                user.setEmail(email);
                logMessage.append("email, ");
            }
            if (password != null) {
                user.setPassword(password);
                logMessage.append("password, ");
            }
            if (phoneNumber != null) {
                user.setPhoneNumber(phoneNumber);
                logMessage.append("phoneNumber, ");
            }
            if (role != null) {
                user.setRole(role);
                logMessage.append("role, ");
            }
            if (name != null) {
                user.setName(name);
                logMessage.append("name, ");
            }

            if (emailNotification != null) {
                user.setEmailNotification(emailNotification);
                logMessage.append("emailNotification, ");
            }

            if (whatsappNotification != null) {
                user.setWhatsappNotification(whatsappNotification);
                logMessage.append("whatsappNotification, ");
            }

            userRepository.save(user);

            if (logMessage.toString().endsWith(", ")) {
                logMessage.delete(logMessage.length() - 2, logMessage.length()); // Remove trailing comma and space
            } else {
                logMessage = new StringBuilder("Nothing updated for user with ID " + id);
            }
        }
        return new UserResponseObj(logMessage.toString());
    }


    @Override
    public UserResponseObj deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String name = user.getName();
            userRepository.deleteById(user.getUserId());
            String logMessage = "User with ID " + id + " and name " + name + " has been deleted.";
            return new UserResponseObj(logMessage);
        } else {
            String logMessage = "User with ID " + id + " not found.";
            return new UserResponseObj(logMessage);
        }
    }

    private Optional<User> findUserbyEmail(String email){
        return userRepository.findByEmail(email);
    }

}
