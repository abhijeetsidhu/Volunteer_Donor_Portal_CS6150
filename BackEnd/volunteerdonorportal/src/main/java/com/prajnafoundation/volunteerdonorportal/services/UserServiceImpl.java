package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.User;
import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;
import com.prajnafoundation.volunteerdonorportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prajnafoundation.volunteerdonorportal.models.UserRole;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A UserResponseObj containing the list of users and a log message.
     */
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

    /**
     * Retrieves a user by their unique identifier (ID).
     *
     * @param id The unique identifier of the user.
     * @return A UserResponseObj containing the user information and a log message.
     */
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
        logMessage = "Successfully found the user with Id: "+ id;
        return new UserResponseObj(user, logMessage);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return A UserResponseObj containing the user information and a log message.
     */
    @Override
    public UserResponseObj getUserByEmail(String email) {
        String logMessage;
        if (email == null){
            logMessage = "No user exist with email " + email +"!";
            return new UserResponseObj(logMessage);
        }
        Optional<User> userOptional = findUserByEmail(email);

        List<User> user = userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);

        if (user.isEmpty()) {
            logMessage = "No user exist with email " + email + "!";
            return new UserResponseObj(logMessage);
        }
        logMessage = "Successfully found the user with Email: "+ email;
        return new UserResponseObj(user, logMessage);
    }

    /**
     * Authenticates a user based on their email and password.
     *
     * @param email    The email address of the user.
     * @param password The password provided by the user.
     * @return A UserResponseObj indicating the authentication status and a log message.
     */
    @Override
    public UserResponseObj authenticateUser(String email, String password) {
        Optional<User> userOptional = findUserByEmail(email);

        if (userOptional.isEmpty()) {
            String logMessage = "Incorrect Credentials: Email";
            return new UserResponseObj(logMessage);
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            String logMessage = "Incorrect Credentials: Password";
            return new UserResponseObj(logMessage);
        }

        String logMessage = "Success!";
        List<User> userObjList = userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);
        return new UserResponseObj(userObjList, logMessage);
    }

    /**
     * Creates a new user profile with the provided information. Ensures that the provided email is unique,
     * validates the role (with ENUM), and performs necessary checks before saving the user to the database.
     *
     * @param email               The email address of the user.
     * @param password            The password for the user.
     * @param phoneNumber         The phone number of the user.
     * @param role                The role of the user (ADMIN, STAFF, MEMBER).
     * @param name                The name of the user.
     * @param dob                 The date of birth of the user.
     * @param emailNotification   Whether the user has opted in for email notifications.
     * @param whatsappNotification Whether the user has opted in for WhatsApp notifications.
     * @return A UserResponseObj indicating the status of the profile creation and a log message.
     */

    @Override
    @Transactional
    public UserResponseObj createUser(String email, String password, String phoneNumber, String role,
                                      String name, Date dob, boolean emailNotification, boolean whatsappNotification) {
        // Check if a user with the given email already exists
        Optional<User> existingUser = findUserByEmail(email);
        String logMessage;
        if (existingUser.isPresent()) {
            logMessage = "User with email " + email + " already exists. Profile creation denied!";
            return new UserResponseObj(logMessage);
        }

        UserRole userRole = UserRole.safeValueOf(role);
        if (userRole == null) {
            logMessage = "Invalid role provided: " + role;
            return new UserResponseObj(logMessage);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRole(userRole);
        user.setName(name);
        user.setDob(dob);
        user.setEmailNotification(emailNotification);
        user.setWhatsappNotification(whatsappNotification);
        userRepository.save(user);

        logMessage = userRole + " profile created for user: " + name;
        return new UserResponseObj(logMessage);
    }

    /**
     * Updates an existing user profile based on the provided information. Performs validations,
     * checks for uniqueness, and updates only the provided fields while ensuring data integrity.
     * Also, logMessage includes the field that have been updated.
     *
     * @param id                  The unique identifier of the user to be updated.
     * @param email               The updated email address of the user.
     * @param password            The updated password for the user.
     * @param phoneNumber         The updated phone number of the user.
     * @param role                The updated role of the user (ADMIN, STAFF, MEMBER).
     * @param name                The updated name of the user.
     * @param emailNotification   The updated email notification preference.
     * @param whatsappNotification The updated WhatsApp notification preference.
     * @return A UserResponseObj indicating the status of the profile update and a log message.
     */
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
                Optional<User> existingUser = findUserByEmail(email);
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
                UserRole userRole = UserRole.safeValueOf(role);
                if (userRole == null) {
                    logMessage = new StringBuilder("Invalid role provided: " + role);
                    return new UserResponseObj(logMessage.toString());
                }
                user.setRole(userRole);
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

    /**
     * Deletes a user profile by their unique identifier.
     * Must also handle a referential integrity issues as the REGISTRATION table
     * references both users and events.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return A UserResponseObj indicating the status of the profile deletion and a log message.
     */
    @Override
    public UserResponseObj deleteUser(Long id) {
        /*
        TODO: Handle cases where a user is registered for an event.
          Deletion might result in referential integrity issues as the REGISTRATION table references
          both users and events.
         */

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String name = user.getName();
            String role = user.getRole();
            userRepository.deleteById(user.getUserId());
            String logMessage = role + " user with ID " + id + " and name " + name + " has been deleted.";
            return new UserResponseObj(logMessage);
        } else {
            String logMessage = "User with ID " + id + " not found.";
            return new UserResponseObj(logMessage);
        }
    }

    private Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
