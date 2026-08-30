package org.practice.design.adapter;

public class StripePaymentAdapter implements PaymentProcessor {

    StripeGateway stripeGateway;

    public StripePaymentAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment() {
        this.stripeGateway.makePayment();
    }
}
