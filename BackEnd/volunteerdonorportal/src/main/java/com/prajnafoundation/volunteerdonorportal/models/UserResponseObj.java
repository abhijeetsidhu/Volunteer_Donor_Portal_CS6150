package com.prajnafoundation.volunteerdonorportal.models;

import com.prajnafoundation.volunteerdonorportal.entities.User;


import java.util.List;

public class UserResponseObj {

    private List<UserObj> users = null;

    public UserResponseObj(List<User> users) {
        this.users = users.stream().map(UserObj::new).toList();
    }

    public List<UserObj> getUsers() {
        return users;
    }

    public void setUsers(List<UserObj> users) {
        this.users = users;
    }


    private static class UserObj {
        Long userId;
        String name;
        String email;
        String password;
        String phoneNumber;
        String role;

        public UserObj (User user) {
            this.userId = user.getUserId();
            name = user.getName();
            email = user.getEmail();
            password = "********";
            phoneNumber = user.getPhoneNumber();
            role = user.getRole();
        }

        public Long getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getRole() {
            return role;
        }
    }
}
