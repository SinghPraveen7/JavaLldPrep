package org.practice.design.state;

public class VendingMachine {

    private State state;

    public VendingMachine() {
        this.state = new NoCoinState();
    }

    public void insertCoin() {
        this.state = this.state.insertCoin();
    }

    public void dispense() {
        this.state = this.state.dispense();
    }


}
