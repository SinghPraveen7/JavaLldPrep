package org.practice.design.practice.uber;

public class PriceService {

    PriceStrategy priceStrategy;

    public PriceService(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public double estimatePrice(Ride ride) {
        return priceStrategy.calculatePrice(ride);
    }

}
