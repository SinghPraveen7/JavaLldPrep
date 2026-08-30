package org.practice.design.practice.movie_booking_system;

import java.util.List;

public class OrchestratorService {

    private PaymentService paymentService;
    private BookingService bookingService;
    private AmountCalculationStrategy amountCalculationStrategy;

    Ticket initBooking(List<Seat> seats, Show show, User user) {
        Ticket ticket = bookingService.holdBooking(seats, show, user);
        if (ticket == null) {
            System.out.println("Seat lock failed!");
        } else {
            Double amount = amountCalculationStrategy.calculateBookingAmount(seats, show);
            Payment payment = paymentService.processPayment(amount);
            if (payment.getStatus() == PaymentStatus.Success) {
                ticket = bookingService.confirmBooking(ticket, payment);
                if (ticket.getStatus() == OrderStatus.Expired) {
                    paymentService.refund(amount, ticket.getPaymentId());
                }
            } else {
                ticket = bookingService.unholdBooking(ticket, payment);
            }
        }
        return ticket;
    }
}
