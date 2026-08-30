package org.practice.design.practice.movie_booking_system;

import java.util.Date;
import java.util.List;

public class BookingService {

    private SeatSelectionService seatSelectionService;

    public Ticket holdBooking(List<Seat> seats, Show show, User user) {
        boolean isSeatLocked = seatSelectionService.lockSeats(seats, user.getUserId(), show);
        if (isSeatLocked) {
            Ticket holdTicket = new Ticket();
            holdTicket.setBookedSeats(seats);
            holdTicket.setOrderId(generateUniqueOrderId());
            holdTicket.setShow(show);
            holdTicket.setStatus(OrderStatus.Pending);
            holdTicket.setUserId(user.getUserId());
            holdTicket.setOrderTime(new Date().getTime());
            return holdTicket;
        }
        return null;
    }

    private String generateUniqueOrderId() {
        // Will keep orderId generation logic here
        return null;
    }

    public Ticket confirmBooking(Ticket ticket, Payment payment) {
        ticket.setPaymentId(payment.getPaymentId());
        if (seatSelectionService.isSeatLocked(ticket.getBookedSeats(), ticket.getUserId(), ticket.getShow())) {
            ticket.setStatus(OrderStatus.Success);
        } else {
            // 10 min time expired
            ticket.setStatus(OrderStatus.Expired);
        }
        return ticket;
    }

    public Ticket unholdBooking(Ticket ticket, Payment payment) {
        ticket.setStatus(OrderStatus.Failure);
        seatSelectionService.unlockSeats(ticket.getBookedSeats(), ticket.getUserId(), ticket.getShow());
        return ticket;
    }

}
