package org.practice.design.practice.movie_booking_system;

import java.util.List;

public interface AmountCalculationStrategy {
    double calculateBookingAmount(List<Seat> seats, Show show);
}
