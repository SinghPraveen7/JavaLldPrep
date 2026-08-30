package org.practice.design.factory.simple;

public class UPIPayment implements Payment {

    @Override
    public void pay() {
        System.out.println("Payment via UPI!");
    }
}
