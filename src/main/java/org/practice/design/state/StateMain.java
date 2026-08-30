package org.practice.design.state;

/**
 * The State Pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes.
 * Instead of using large if-else or switch statements based on state, state-specific behavior is encapsulated into separate classes.
 * The context object delegates behavior to the current state object, and state transitions occur internally.
 * A classic example is an order management system where an order transitions through states such as Created, Paid, Shipped, and Delivered,
 * with each state supporting different operations.
 * The State Pattern improves maintainability, follows the Open-Closed Principle, and makes state transitions explicit and easier to manage.
 */
public class StateMain {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.dispense();
        vendingMachine.insertCoin();
        vendingMachine.dispense();
        vendingMachine.dispense();
    }
}
