package com.example.setupark.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpaceTest {

    private ParkingSpace space;
    private LocalDateTime now;

    @BeforeEach
    void setup() {
        space = new ParkingSpace("A1", ParkingSpace.State.AVAILABLE);
        // 10am 1/1/26
        now = LocalDateTime.of(2026, 1, 1, 10, 0);
    }

    @Test
    void spaceIDTest() {
        assertEquals("A1", space.getSpaceID());
    }

    @Test
    void stateTest() {
        assertEquals(ParkingSpace.State.AVAILABLE, space.getState());
    }

    @Test
    void setSpaceIDTest() {
        space.setSpaceID("B2");
        assertEquals("B2", space.getSpaceID());
    }

    @Test
    void setStateTest() {
        space.setState(ParkingSpace.State.RESERVED);
        assertEquals(ParkingSpace.State.RESERVED, space.getState());
    }

    @Test
    void setReservedByUserIDTest() {
        User user123 = new User("user123", User.Role.STAFF);
        space.setReservedByUser(user123);
        assertEquals(user123, space.getReservedByUser());
    }

    @Test
    void setOccupiedByUserIDTest() {
        User user456 = new User("user456", User.Role.STAFF);
        space.setOccupiedByUser(user456);
        assertEquals(user456, space.getOccupiedByUser());
    }

    @Test
    void setReservationStartTest() {
        space.setReservationStart(now);
        assertEquals(now, space.getReservationStart());
    }

    @Test
    void setReservationEndTest() {
        LocalDateTime end = now.plusHours(2);
        space.setReservationEnd(end);
        assertEquals(end, space.getReservationEnd());
    }
}
