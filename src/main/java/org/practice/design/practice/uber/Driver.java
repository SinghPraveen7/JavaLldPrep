package org.practice.design.practice.uber;

public class Driver {
    Location location;
    String id;
    String name;
    //online offline

    LocationService locationService;
    RideService rideService;

    public void updateLocation() {
        //Client Keep calling after every 5 sec to update location of Driver
        locationService.updateDriverLocation(this.location);
    }

    public boolean getRideRequest(Ride ride) {
        // Driver will take ride request and respond true or false;
        return true;
    }

    public void startRide(Ride ride) {
        rideService.startRide(ride);
    }

    public void endRide(Ride ride) {
        rideService.endRide(ride);
    }

}
