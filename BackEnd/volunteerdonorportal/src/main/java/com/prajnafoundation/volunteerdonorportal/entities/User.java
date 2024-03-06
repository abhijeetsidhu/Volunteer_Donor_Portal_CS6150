package com.prajnafoundation.volunteerdonorportal.entities;

import com.prajnafoundation.volunteerdonorportal.models.UserRole;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "name")
    private String name;

    @Column(name = "email_notification")
    private boolean emailNotification;

    @Column(name = "whatsapp_notification")
    private boolean whatsappNotification;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean optedInForEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public boolean optedInForWhatsappNotification() {
        return whatsappNotification;
    }

    public void setWhatsappNotification(boolean whatsappNotification) {
        this.whatsappNotification = whatsappNotification;
    }

    public String getRole() {
        return role.name();
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEmailNotification() {
        return emailNotification;
    }

    public boolean isWhatsappNotification() {
        return whatsappNotification;
    }
}

