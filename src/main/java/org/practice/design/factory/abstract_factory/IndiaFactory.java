package org.practice.design.factory.abstract_factory;

public class IndiaFactory implements AppFactory {

    @Override
    public Notification getNotificationSystem() {
        return new SMSNotification();
    }

    @Override
    public Payment getPaymentSystem() {
        return new UPIPayment();
    }

}
