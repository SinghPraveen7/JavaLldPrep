package org.practice.design.practice.parking_lot;

import java.util.List;

public interface SpotAllocationStrategy {

    ParkingSpot getFreeSpot(Size size, List<ParkingSpot> parkingSpots);

}
