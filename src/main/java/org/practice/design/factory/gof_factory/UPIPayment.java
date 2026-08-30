package org.practice.design.factory.gof_factory;

public class UPIPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Paying via UPI");
    }
}
