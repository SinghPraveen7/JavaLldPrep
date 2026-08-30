package org.practice.design.practice.uber;

public class SurgedPriceStrategy implements PriceStrategy {
    @Override
    public double calculatePrice(Ride ride) {
        return ride.distance * 20 * 1.25; // 25% price added as surged
    }
}
