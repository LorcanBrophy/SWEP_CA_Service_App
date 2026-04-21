package com.example.setupark.model;

public class User {

    // fields
    private String userID;
    private Role role;

    public enum Role {
        STAFF,
        STUDENT,
        VISITOR,
        ADMIN
    }

    // constructor
    public User(String userID, Role role) {
        this.userID = userID;
        this.role = role;
    }

    // getters
    public String getUserID() {
        return userID;
    }
    public Role getRole() {
        return role;
    }

    // setters
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
