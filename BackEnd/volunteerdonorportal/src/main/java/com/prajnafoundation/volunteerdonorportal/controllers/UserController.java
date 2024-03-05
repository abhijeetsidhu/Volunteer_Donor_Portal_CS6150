package com.prajnafoundation.volunteerdonorportal.controllers;

import com.prajnafoundation.volunteerdonorportal.models.UserResponseObj;
import com.prajnafoundation.volunteerdonorportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponseObj getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseObj getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @GetMapping("/login")
//    public boolean login(@RequestParam String email, @RequestParam String password) {
//        return userService.validatePassword(email, password);
//    }

    @PostMapping
    public String createUser(@RequestParam String email, @RequestParam String password, @RequestParam String phoneNumber,
                             @RequestParam String role, @RequestParam String name) {
        return userService.createUser(email, password, phoneNumber, role, name);
    }

    // Endpoint to update an existing user by ID
    @PutMapping
    public String updateUser(@RequestParam Long userId, @RequestParam String email, @RequestParam String password,
                             @RequestParam String phoneNumber, @RequestParam String role, @RequestParam String name) {
        return userService.updateUser(userId, email, password, phoneNumber, role, name);
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
