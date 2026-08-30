package org.practice.design.factory.gof_factory;

/**
 * A Simple Factory is a utility class that creates objects based on input parameters, usually using if-else or switch statements.
 * It is not an official GoF pattern and often violates the Open-Closed Principle because the factory must be modified whenever a new product type is added.
 * In contrast, the GoF Factory Method pattern defines an abstract factory method and lets subclasses decide which concrete object to create.
 * It uses inheritance and polymorphism, supports the Open-Closed Principle, and is more extensible.
 * In short, Simple Factory centralizes object creation, whereas Factory Method delegates object creation to subclasses.
 */
public class FactoryMain {

    public static void main(String[] args) {
        PaymentFactory paymentFactory = new CardPaymentFactory();
        paymentFactory.doPayment();

        PaymentFactory upiFactory = new UPIPaymentFactory();
        upiFactory.doPayment();
    }

}
