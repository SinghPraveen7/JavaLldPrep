package org.practice.design.practice.parking_lot;

import java.util.Date;

public class EntranceGate {
    private Integer gateNo;
    private ParkingLot parkingLot;

    public EntranceGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = this.parkingLot.getFreeSpot(vehicle.getVehicleSize());
        if (spot == null) {
            System.out.println("No spot available for vehicle!");
            return null;
        }
        return generateTicket(spot, vehicle);
    }

    private Ticket generateTicket(ParkingSpot spot, Vehicle vehicle) {
        Ticket ticket = new Ticket();
        ticket.setVehicleNumber(vehicle.getVehicleNumber());
        ticket.setParkingSpot(spot);
        ticket.setInTime(new Date());
        return ticket;
    }
}
