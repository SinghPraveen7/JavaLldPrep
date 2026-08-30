package org.practice.design.factory.abstract_factory;

/**
 * Factory Method creates a single type of product and lets subclasses decide which concrete implementation to instantiate.
 * Abstract Factory creates a family of related products together. For example, a PaymentFactory may create only payment objects
 * such as UPI or Credit Card payments, whereas an IndiaFactory may create a UPI payment, SMS notification service, and
 * India-specific tax calculator as a related group of objects. Factory Method focuses on creating one product hierarchy,
 * while Abstract Factory focuses on creating multiple related product families consistently.
 */
public class AFMain {

    public static void main(String[] args) {
        AppFactory indiaFactory = new IndiaFactory();
        indiaFactory.getNotificationSystem().sendNotification();
        indiaFactory.getPaymentSystem().pay();


        AppFactory usaFactory = new USAFactory();
        usaFactory.getNotificationSystem().sendNotification();
        usaFactory.getPaymentSystem().pay();
    }

}
