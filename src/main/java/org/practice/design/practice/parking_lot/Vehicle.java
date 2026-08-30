package org.practice.design.practice.parking_lot;

public class Vehicle {

    private String vehicleNumber;
    private Size vehicleSize;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Size getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(Size vehicleSize) {
        this.vehicleSize = vehicleSize;
    }
}
