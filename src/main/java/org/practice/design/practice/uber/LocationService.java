package org.practice.design.practice.uber;

public class LocationService {

    SearchDriverStrategy searchDriverStrategy;

    public void updateDriverLocation(Location driverLocation) {
        // will update driver's location in DB
    }

    public Driver findNearbyDriver(Location sourceLocation) {
        return searchDriverStrategy.findDriver(sourceLocation);
    }

    public double calculateDistance(Location source, Location destination) {
        //Will have some external map service to find path and then calculate distance and return distance
        return 0d;
    }

}
