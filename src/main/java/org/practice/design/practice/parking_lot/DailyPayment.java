package org.practice.design.practice.parking_lot;

public class DailyPayment implements PaymentStrategy {
    @Override
    public double processPayment(long value) {
        return (value/86400000f) * 100;
    }
}
