package com.prajnafoundation.volunteerdonorportal.models;

import com.prajnafoundation.volunteerdonorportal.entities.User;

import java.util.Date;
import java.util.List;

public class UserResponseObj extends LogMessage {

    private List<UserObj> users = null;

    public UserResponseObj(List<User> users, String logMessage) {
        super(logMessage);
        this.users = users.stream().map(UserObj::new).toList();
    }

    public UserResponseObj(String logMessage) {
        super(logMessage);
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
        Date dob;
        boolean emailNotification;
        boolean whatsappNotification;

        public UserObj(User user) {
            this.userId = user.getUserId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.phoneNumber = user.getPhoneNumber();
            this.role = user.getRole();
            this.dob = user.getDob();
            this.emailNotification = user.optedInForEmailNotification();
            this.whatsappNotification = user.optedInForWhatsappNotification();
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

        public Date getDob() {
            return dob;
        }

        public boolean isEmailNotification() {
            return emailNotification;
        }

        public boolean isWhatsappNotification() {
            return whatsappNotification;
        }
    }
}
