package org.practice.design.factory.abstract_factory;

public class CardPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Payment via card!");
    }
}
