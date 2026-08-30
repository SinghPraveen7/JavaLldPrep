package org.practice.design.adapter;

/**
 * The Adapter Pattern is a structural design pattern used to make two incompatible interfaces work together.
 * It acts as a translator between a client and an existing class whose interface does not match the client's expectations.
 * The adapter implements the target interface expected by the client and internally delegates calls to the adaptee.
 * A common example is integrating third-party payment gateways such as Stripe or PayPal into a common payment processing interface.
 * In Java, InputStreamReader is a classic example because it adapts a byte stream (InputStream) into a character stream (Reader).
 * The main benefit is that existing code can be reused without modification while maintaining loose coupling.
 */
public class AdapterMain {
    public static void main(String[] args) {
        PaymentProcessor stripePaymentProcessor = new StripePaymentAdapter(new StripeGateway());
        stripePaymentProcessor.processPayment();
        PaymentProcessor paypalPaymentProcessor = new PaypalPaymentAdapter(new PaypalGateway());
        paypalPaymentProcessor.processPayment();
    }
}
