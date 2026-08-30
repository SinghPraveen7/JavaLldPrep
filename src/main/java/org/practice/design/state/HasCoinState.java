package org.practice.design.state;

public class HasCoinState implements State {


    @Override
    public State insertCoin() {
        System.out.println("Already has coin");
        return new HasCoinState();
    }

    @Override
    public State dispense() {
        System.out.println("Dispensing Item...");
        return new NoCoinState();
    }
}
