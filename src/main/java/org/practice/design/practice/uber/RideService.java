package org.practice.design.practice.uber;

public class RideService {

    LocationService locationService;
    PriceService priceService;

    public Ride createRide(Location source, Location destination) {
        Ride ride = new Ride();
        ride.source = source;
        ride.destination = destination;
        ride.status = RideStatus.REQUESTED; // Created
        ride.distance = locationService.calculateDistance(source, destination);
        ride.estimatedFare = priceService.estimatePrice(ride); // also can only pass distance
        // Also store this in repo
        return ride;
    }

    // User call
    public boolean requestRide(Ride ride) {
        ride.status = RideStatus.REQUESTED;
        Driver driver = locationService.findNearbyDriver(ride.source);
        return sendRideRequest(driver, ride);
    }

    // Driver call
    public boolean acceptRide(boolean isAccepted, Ride ride) {
        if (isAccepted) {
            ride.status = RideStatus.ACCEPTED;
        } else {
            // Can have multiple retries with different nearby drivers
            ride.status = RideStatus.REJECTED;
        }
        return isAccepted;
    }

    // Driver call
    public void startRide(Ride ride) {
        ride.status = RideStatus.IN_PROGRESS;
    }

    // Driver call
    public void endRide(Ride ride) {
        ride.status = RideStatus.COMPLETED;
    }

    //This method will wait for 10 seconds for driver's response
    public boolean sendRideRequest(Driver driver, Ride ride) {
        boolean isAcceptedRequest = driver.getRideRequest(ride);
        return acceptRide(isAcceptedRequest, ride);
    }

}
