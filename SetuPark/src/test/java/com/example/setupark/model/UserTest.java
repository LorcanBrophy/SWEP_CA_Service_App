package com.example.setupark.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("user123", User.Role.STAFF);
    }

    @Test
    void userIDTest() {
        assertEquals("user123", user.getUserID());
    }

    @Test
    void roleTest() {
        assertEquals(User.Role.STAFF, user.getRole());
    }

    @Test
    void setUserIDTest() {
        user.setUserID("user456");
        assertEquals("user456", user.getUserID());
    }

    @Test
    void setRoleAdminTest() {
        user.setRole(User.Role.ADMIN);
        assertEquals(User.Role.ADMIN, user.getRole());
    }

    @Test
    void setRoleVisitorTest() {
        user.setRole(User.Role.VISITOR);
        assertEquals(User.Role.VISITOR, user.getRole());
    }

    @Test
    void setRoleStudentTest() {
        user.setRole(User.Role.STUDENT);
        assertEquals(User.Role.STUDENT, user.getRole());
    }
}
