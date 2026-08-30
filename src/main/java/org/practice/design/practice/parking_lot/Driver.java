package org.practice.design.practice.parking_lot;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        // Creating Parking lot
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.setParkingSpots(createDemoSpots()); // For demo purpose only, will populate data from repository
        parkingLot.setPaymentStrategy(new HourlyPayment());
        parkingLot.setSpotAllocationStrategy(new FirstFreeSpotStrategy());
        // Creating Vehicle
        Vehicle creta = new Vehicle();
        creta.setVehicleNumber("UP32-1000");
        creta.setVehicleSize(Size.LARGE);
        //Gates knows there parking lot
        EntranceGate entranceGate = new EntranceGate(parkingLot);
        ExitGate exitGate = new ExitGate(parkingLot);
        //Parking Vehicle
        Ticket ticket = entranceGate.parkVehicle(creta);
        if (ticket == null) {
            System.out.println("Better Luck Next time");
        } else {
            Thread.sleep(10);
            //Unparking Vehicle
            ticket = exitGate.unparkVehicle(ticket);
            System.out.println("Total Parking Fare: " + ticket.getFare());
        }

    }

    private static List<ParkingSpot> createDemoSpots() {
        List<ParkingSpot> spots = new ArrayList<>();
        ParkingSpot parkingSpot1 = new ParkingSpot();
        parkingSpot1.setSpotId("1121");
        parkingSpot1.setSpotSize(Size.LARGE);
        parkingSpot1.setEmpty(true);
        spots.add(parkingSpot1);
        ParkingSpot parkingSpot2 = new ParkingSpot();
        parkingSpot2.setSpotId("1122");
        parkingSpot2.setSpotSize(Size.MEDIUM);
        parkingSpot2.setEmpty(true);
        spots.add(parkingSpot2);
        return spots;
    }
}
