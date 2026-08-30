package org.practice.design.adapter;

public class PaypalPaymentAdapter implements PaymentProcessor {

    PaypalGateway paypalGateway;

    public PaypalPaymentAdapter(PaypalGateway paypalGateway) {
        this.paypalGateway = paypalGateway;
    }

    @Override
    public void processPayment() {
        paypalGateway.doPayment();
    }
}
