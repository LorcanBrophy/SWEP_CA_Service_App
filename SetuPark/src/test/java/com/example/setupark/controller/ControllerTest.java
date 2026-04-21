package com.example.setupark.controller;

import com.example.setupark.model.ParkingSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class ControllerTest {

    private Controller controller;
    private ParkingSpace space;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        space = new ParkingSpace("A1", ParkingSpace.State.AVAILABLE);
        controller.addSpace(space);
        now = LocalDateTime.of(2026, 1, 1, 10, 0); // 10am 1/1/26
    }

    // crud

    @Test
    void addSpaceTest() {
        assertNotNull(controller.getSpace("A1"));
    }

    @Test
    void removeSpaceByIDTest() {
        controller.removeSpace("A1");
        assertNull(controller.getSpace("A1"));
    }

    @Test
    void removeSpaceByObjectTest() {
        controller.removeSpace(space);
        assertNull(controller.getSpace("A1"));
    }

    // reserve

    @Test
    void reserveAvailableSpaceTest() {
        boolean result = controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        assertTrue(result);
    }

    @Test
    void reserveChangesStateToReservedTest() {
        controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        assertEquals(ParkingSpace.State.RESERVED, space.getState());
    }

    // occupied

    @Test
    void reserveOccupiedSpaceTest() {
        space.setState(ParkingSpace.State.OCCUPIED);
        boolean result = controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        assertFalse(result);
    }

    @Test
    void occupyChangesStateToOccupiedTest() {
        controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        controller.occupySpace("A1", "user123");
        assertEquals(ParkingSpace.State.OCCUPIED, space.getState());
    }

    @Test
    void occupyFailsWithWrongUserTest() {
        controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        boolean result = controller.occupySpace("A1", "user1234");
        assertFalse(result);
    }

    @Test
    void occupyFailsIfNotReservedStateTest() {
        space.setState(ParkingSpace.State.AVAILABLE);
        boolean result = controller.occupySpace("A1", "user123");
        assertFalse(result);
    }

    // release space

    @Test
    void releaseSetsStateToAvailableTest() {
        controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        controller.releaseSpace("A1");
        assertEquals(ParkingSpace.State.AVAILABLE, space.getState());
    }

    // expiration branches

    @Test
    void expiredReservationBecomesAvailableTest() {
        controller.reserveSpace("A1", "user123", now, now.minusHours(1));
        controller.expired(now);
        assertEquals(ParkingSpace.State.AVAILABLE, space.getState());
    }

    @Test
    void expiredDoesNothingIfNotPastEndTimeTest() {
        controller.reserveSpace("A1", "user123", now, now.plusHours(1));
        controller.expired(now);
        assertEquals(ParkingSpace.State.RESERVED, space.getState());
    }

    @Test
    void expiredDoesNothingIfNotReservedTest() {
        space.setState(ParkingSpace.State.OCCUPIED);
        controller.expired(now);
        assertEquals(ParkingSpace.State.OCCUPIED, space.getState());
    }

    @Test
    void expiredDoesNothingIfEndIsNullTest() {
        space.setState(ParkingSpace.State.RESERVED);
        space.setReservationEnd(null);
        controller.expired(now);
        assertEquals(ParkingSpace.State.RESERVED, space.getState());
    }


}
