package org.practice.design.practice.uber;

public class UberMain {

    public static void main(String[] args) {
        RideService uber = new RideService();
        Location source = null;
        Location destination = null;
        Ride ride = uber.createRide(source, destination);
        boolean isRequestAccepted = uber.requestRide(ride);
        uber.startRide(ride);
        uber.endRide(ride);
    }
}
