package org.practice.design.practice.movie_booking_system;

public interface PaymentService {

    Payment processPayment(double amount);

    void refund(Double amount, String paymentId);
}
