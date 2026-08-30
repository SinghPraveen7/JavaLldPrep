package org.practice.design.factory.simple;

public class CardPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Payment via Card!");
    }
}
