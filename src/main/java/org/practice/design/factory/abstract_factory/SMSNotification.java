package org.practice.design.factory.abstract_factory;

public class SMSNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS...");
    }
}
