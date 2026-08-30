package org.practice.design.factory.gof_factory;

public class UPIPaymentFactory extends PaymentFactory {

    @Override
    Payment getPayment() {
        return new UPIPayment();
    }
}
