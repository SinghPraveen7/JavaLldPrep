package org.practice.design.practice.parking_lot;

public class ParkingSpot {

    private String spotId;
    private Size spotSize;
    private boolean isEmpty;

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public Size getSpotSize() {
        return spotSize;
    }

    public void setSpotSize(Size spotSize) {
        this.spotSize = spotSize;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
