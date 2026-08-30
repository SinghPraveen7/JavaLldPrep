package org.practice.design.practice.parking_lot;

import java.util.List;

public class FirstFreeSpotStrategy implements SpotAllocationStrategy {

    @Override
    public ParkingSpot getFreeSpot(Size size, List<ParkingSpot> parkingSpots) {
        for (ParkingSpot spot: parkingSpots) {
            if ((spot.getSpotSize() == size) && spot.isEmpty()) {
                spot.setEmpty(false); return spot;
            }
        } return null;
    }
}
