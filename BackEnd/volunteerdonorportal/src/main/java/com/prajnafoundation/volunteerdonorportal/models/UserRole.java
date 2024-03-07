package com.prajnafoundation.volunteerdonorportal.models;

public enum UserRole {
    ADMIN,
    STAFF,
    MEMBER;

    public static UserRole safeValueOf(String value) {
        try {
            return UserRole.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
