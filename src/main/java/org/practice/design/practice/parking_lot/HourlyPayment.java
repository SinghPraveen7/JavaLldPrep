package org.practice.design.practice.parking_lot;

public class HourlyPayment implements PaymentStrategy {
    @Override
    public double processPayment(long value) {
        return (value) * 10; // This is just for testing purpose, below is the correct implementation
        //return (value/3600000f) * 10;
    }
}
