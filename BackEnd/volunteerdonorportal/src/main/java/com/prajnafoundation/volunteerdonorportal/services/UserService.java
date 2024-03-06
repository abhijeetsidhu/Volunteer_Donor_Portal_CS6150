package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;

import java.util.Date;
public interface UserService {

    UserResponseObj getAllUsers();

    UserResponseObj getUserById(Long id);

    UserResponseObj getUserByEmail(String email);

    UserResponseObj authenticateUser(String email, String password);

    UserResponseObj createUser(String email, String password, String phoneNumber, String role,
                               String name, Date dob, boolean emailNotification, boolean whatsappNotification);

    UserResponseObj updateUser(Long id, String email, String password, String phoneNumber, String role,
               String name, Boolean emailNotification, Boolean whatsappNotification);

    UserResponseObj deleteUser(Long id);
}
