package org.practice.design.factory.gof_factory;

public abstract class PaymentFactory {

    abstract Payment getPayment();

    public void doPayment() {
        Payment payment = getPayment();
        payment.pay();
    }

}
