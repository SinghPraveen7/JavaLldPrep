package org.practice.design.practice.parking_lot;

public class ExitGate {
    private Integer gateNo;
    private ParkingLot parkingLot;

    public ExitGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }


    public Ticket unparkVehicle(Ticket ticket) {
        long elapsedTime = ticket.calculateParkingDuration();
        double amount = parkingLot.calculateFare(elapsedTime);
        ticket.setFare(amount);
        parkingLot.freeSpot(ticket.getParkingSpot());
        return ticket;
    }
}
