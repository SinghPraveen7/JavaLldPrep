package org.practice.design.practice.parking_lot;


import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> parkingSpots;
    private PaymentStrategy paymentStrategy;
    private SpotAllocationStrategy spotAllocationStrategy;
    private static final ParkingLot INSTANCE = new ParkingLot();

    //Singleton class
    private ParkingLot() {
    }

    public static ParkingLot getParkingLot() {
        return INSTANCE;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setSpotAllocationStrategy(SpotAllocationStrategy spotAllocationStrategy) {
        this.spotAllocationStrategy = spotAllocationStrategy;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }


    public synchronized ParkingSpot getFreeSpot(Size vehicleSize) {
        return spotAllocationStrategy.getFreeSpot(vehicleSize, parkingSpots);
    }

    public synchronized void freeSpot(ParkingSpot spot) {
        spot.setEmpty(true);
    }

    public double calculateFare(long elapsedTime) {
        return paymentStrategy.processPayment(elapsedTime);
    }
}
