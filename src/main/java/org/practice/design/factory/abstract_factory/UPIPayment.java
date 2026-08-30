package org.practice.design.factory.abstract_factory;

public class UPIPayment implements Payment {

    @Override
    public void pay() {
        System.out.println("Payment via UPI!");
    }
}
