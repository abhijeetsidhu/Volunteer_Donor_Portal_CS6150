package com.prajnafoundation.volunteerdonorportal.controllers;

import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;
import com.prajnafoundation.volunteerdonorportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponseObj getUsers(@RequestParam(name = "id", required = false) Long id,
                                    @RequestParam(name = "email", required = false) String email) {
        if (id == null && email == null) {
            // Handle the case where no parameters are provided
            return userService.getAllUsers();
        } else {
            UserResponseObj response = userService.getUserById(id);
            if (response.getUsers() != null) {
                return response;
            }
            response = userService.getUserByEmail(email);
            if (response.getUsers() != null) {
                return response;
            }

            StringBuilder logMessage = new StringBuilder("No such user exist with ");

            if(id != null && email != null){
                logMessage.append("id: ").append(id).append(" or " + "Email: ").append(email);
            } else if (id != null && email == null) {
                logMessage.append("id: ").append(id);
            }
            else{
                logMessage.append("Email: ").append(email);
            }
            return new UserResponseObj(logMessage.toString());
        }
    }

    @GetMapping("/login")
    public UserResponseObj authenticateUser(@RequestParam String email, @RequestParam String password) {
        return userService.authenticateUser(email, password);
    }

    @PostMapping
    public UserResponseObj createUser(@RequestParam String email, @RequestParam String password, @RequestParam String phoneNumber,
                             @RequestParam String role, @RequestParam String name, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
                             @RequestParam(required = false, defaultValue = "false") boolean emailNotification,
                             @RequestParam(required = false, defaultValue = "false") boolean whatsappNotification) {
        return userService.createUser(email, password, phoneNumber, role, name, dob, emailNotification, whatsappNotification);
    }

    @PutMapping
    public UserResponseObj updateUser(@RequestParam Long userId, @RequestParam(required = false) String email,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String phoneNumber,
                             @RequestParam(required = false) String role,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Boolean emailNotification,
                             @RequestParam(required = false) Boolean whatsappNotification) {
        return userService.updateUser(userId, email, password, phoneNumber, role, name, emailNotification, whatsappNotification);
    }

    @DeleteMapping("/{id}")
    public UserResponseObj deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
