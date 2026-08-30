package org.practice.design.factory.gof_factory;

public class CardPaymentFactory extends PaymentFactory {
    @Override
    Payment getPayment() {
        return new CardPayment();
    }
}
