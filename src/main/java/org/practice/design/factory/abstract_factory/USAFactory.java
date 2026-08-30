package org.practice.design.factory.abstract_factory;

public class USAFactory implements AppFactory {

    @Override
    public Notification getNotificationSystem() {
        return new EmailNotification();
    }

    @Override
    public Payment getPaymentSystem() {
        return new CardPayment();
    }

}
