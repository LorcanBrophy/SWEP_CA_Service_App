package com.example.setupark.controller;

import com.example.setupark.model.ParkingSpace;
import com.example.setupark.model.User;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    @FXML
    private BarChart<String, Number> peakTimesChart;

    private Map<String, ParkingSpace> spaces = new HashMap<>();

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Occupancy");

        series.getData().add(new XYChart.Data<>("9:00", 588));
        series.getData().add(new XYChart.Data<>("10:00", 535));
        series.getData().add(new XYChart.Data<>("11:00", 432));
        series.getData().add(new XYChart.Data<>("12:00", 265));
        series.getData().add(new XYChart.Data<>("13:00", 258));
        series.getData().add(new XYChart.Data<>("14:00", 162));
        series.getData().add(new XYChart.Data<>("15:00", 144));
        series.getData().add(new XYChart.Data<>("16:00", 292));
        series.getData().add(new XYChart.Data<>("17:00", 355));

        peakTimesChart.getData().add(series);
    }

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
    public boolean reserveSpace(String spaceID, User user, LocalDateTime start, LocalDateTime end) {
        ParkingSpace space = getSpace(spaceID);

        if (space.getState() != ParkingSpace.State.AVAILABLE) return false;

        space.setReservedByUser(user);
        space.setReservationStart(start);
        space.setReservationEnd(end);
        space.setState(ParkingSpace.State.RESERVED);

        return true;
    }

    public boolean occupySpace(String spaceID, User user) {
        ParkingSpace space = getSpace(spaceID);

        if (space.getState() != ParkingSpace.State.RESERVED) return false;

        if (!user.equals(space.getReservedByUser())) return false;

        space.setOccupiedByUser(user);
        space.setState(ParkingSpace.State.OCCUPIED);

        return true;
    }

    public void releaseSpace(String spaceID) {
        ParkingSpace space = getSpace(spaceID);

        space.setReservedByUser(null);
        space.setOccupiedByUser(null);
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
