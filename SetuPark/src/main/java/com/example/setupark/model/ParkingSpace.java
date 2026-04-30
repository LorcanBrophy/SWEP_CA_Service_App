package com.example.setupark.model;

import java.time.LocalDateTime;

public class ParkingSpace {
    // fields
    private String spaceID;
    private State state;

    private User reservedByUser;
    private User occupiedByUser;

    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;

    public enum State {
        AVAILABLE,
        RESERVED,
        OCCUPIED;
    }

    // constructor
    public ParkingSpace(String spaceID, State state) {
        this.spaceID = spaceID;
        this.state = state;
    }

    // getters
    public String getSpaceID() {
        return spaceID;
    }
    public State getState() {
        return state;
    }
    public User getReservedByUser() {
        return reservedByUser;
    }
    public User getOccupiedByUser() {
        return occupiedByUser;
    }
    public LocalDateTime getReservationStart() {
        return reservationStart;
    }
    public LocalDateTime getReservationEnd() {
        return reservationEnd;
    }

    // setters
    public void setSpaceID(String spaceID) {
        this.spaceID = spaceID;
    }
    public void setState(State state) {
        this.state = state;
    }
    public void setReservedByUser(User reservedByUser) {
        this.reservedByUser = reservedByUser;
    }
    public void setOccupiedByUser(User occupiedByUser) {
        this.occupiedByUser = occupiedByUser;
    }
    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }
    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }
}
