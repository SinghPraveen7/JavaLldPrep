package org.practice.design.practice.uber;

public class NormalPriceStrategy implements PriceStrategy {
    @Override
    public double calculatePrice(Ride ride) {
        return ride.distance * 20;
    }
}
