package org.practice.design.factory.simple;

public class SimpleMain {

    public static void main(String[] args) {
        Payment upiPayment = PaymentFactory.getPayment("UPI");
        upiPayment.pay();

        Payment cardPayment = PaymentFactory.getPayment("CARD");
        cardPayment.pay();
    }

}
