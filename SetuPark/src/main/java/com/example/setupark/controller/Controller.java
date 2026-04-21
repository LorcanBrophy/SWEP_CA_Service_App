package com.example.setupark.controller;

import com.example.setupark.model.ParkingSpace;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    private Map<String, ParkingSpace> spaces = new HashMap<>();

    public void addSpace(ParkingSpace space) {
        spaces.put(space.getSpaceID(), space);
    }

    public void removeSpace(String spaceID) {
        spaces.remove(spaceID);
    }

    public void removeSpace(ParkingSpace space) {
        spaces.remove(space.getSpaceID());
    }

    public ParkingSpace getSpace(String spaceID) {
        return spaces.get(spaceID);
    }

    // reservation features
    public boolean reserveSpace(String spaceID, String userID, LocalDateTime start, LocalDateTime end) {
        ParkingSpace space = getSpace(spaceID);

        if (space.getState() != ParkingSpace.State.AVAILABLE) return false;

        space.setReservedByUserID(userID);
        space.setReservationStart(start);
        space.setReservationEnd(end);
        space.setState(ParkingSpace.State.RESERVED);

        return true;
    }

    public boolean occupySpace(String spaceID, String userID) {
        ParkingSpace space = getSpace(spaceID);

        if (space.getState() != ParkingSpace.State.RESERVED) return false;

        if (!userID.equals(space.getReservedByUserID())) return false;

        space.setOccupiedByUserID(userID);
        space.setState(ParkingSpace.State.OCCUPIED);

        return true;
    }

    public void releaseSpace(String spaceID) {
        ParkingSpace space = getSpace(spaceID);

        space.setReservedByUserID(null);
        space.setOccupiedByUserID(null);
        space.setReservationStart(null);
        space.setReservationEnd(null);
        space.setState(ParkingSpace.State.AVAILABLE);
    }

    // if user did not reach space in time
    public void expired(LocalDateTime now) {
        for (ParkingSpace space : spaces.values()) {
            if (space.getState() == ParkingSpace.State.RESERVED && space.getReservationEnd() != null && now.isAfter(space.getReservationEnd())) releaseSpace(space.getSpaceID());
        }
    }






}
