package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;


public interface UserService {

    UserResponseObj getAllUsers();

    UserResponseObj getUserById(Long id);

    String createUser(String email, String password, String phoneNumber,
                    String role, String name);

    String updateUser(Long id, String email, String password,
                      String phoneNumber, String role, String name);

    String deleteUser(Long id);
}
