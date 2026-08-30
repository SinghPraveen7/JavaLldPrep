package org.practice.design.factory.abstract_factory;

public interface AppFactory {
    Notification getNotificationSystem();

    Payment getPaymentSystem();
}
