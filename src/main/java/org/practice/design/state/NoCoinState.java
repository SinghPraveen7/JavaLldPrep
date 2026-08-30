package org.practice.design.state;

public class NoCoinState implements State {

    @Override
    public State insertCoin() {
        System.out.println("Coin is inserted.");
        return new HasCoinState();
    }

    @Override
    public State dispense() {
        System.out.println("Insert Coin first to dispense.");
        return new NoCoinState();
    }
}
