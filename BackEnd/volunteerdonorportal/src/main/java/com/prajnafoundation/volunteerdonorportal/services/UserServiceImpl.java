package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.User;
import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;
import com.prajnafoundation.volunteerdonorportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        return new UserResponseObj(userRepository.findAll());
    }


    @Override
    public UserResponseObj getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return new UserResponseObj(userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList));
    }

    @Override
    @Transactional
    public String createUser(String email, String password, String phoneNumber,
                           String role, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setName(name);
        userRepository.save(user);
        return "User profile created for " + name;
    }

    @Override
    @Transactional
    public String updateUser(Long id, String email, String password,
                             String phoneNumber, String role, String name) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return "User with ID " + id + " not found.";
        } else {
            User user = userOptional.get();
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            user.setRole(role);
            user.setName(name);
            userRepository.save(user);
            return "User with ID " + id + " and name " + user.getName() + " has been updated.";
        }
    }


    @Override
    public String deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(user.getUserId());
                    return "User with ID " + user.getUserId() + " and name " + user.getName() + " has been deleted.";
                })
                .orElse("User with ID " + id + " not found.");

    }
}
