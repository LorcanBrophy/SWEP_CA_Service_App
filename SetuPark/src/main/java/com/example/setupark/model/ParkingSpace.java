package com.example.setupark.model;

import java.time.LocalDateTime;

public class ParkingSpace {
    // fields
    private String spaceID;
    private State state;

    private String reservedByUserID;
    private String occupiedByUserID;

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
    public String getReservedByUserID() {
        return reservedByUserID;
    }
    public String getOccupiedByUserID() {
        return occupiedByUserID;
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
    public void setReservedByUserID(String reservedByUserID) {
        this.reservedByUserID = reservedByUserID;
    }
    public void setOccupiedByUserID(String occupiedByUserID) {
        this.occupiedByUserID = occupiedByUserID;
    }
    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }
    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }
}
