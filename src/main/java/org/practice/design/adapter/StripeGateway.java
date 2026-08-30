package org.practice.design.adapter;

// It has makePayment method, we need processPayment method in our system
public class StripeGateway {

    void makePayment() {
        System.out.println("Payment via Stripe!");
    }

}
