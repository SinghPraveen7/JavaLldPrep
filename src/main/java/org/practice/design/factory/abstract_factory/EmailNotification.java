package org.practice.design.factory.abstract_factory;

public class EmailNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending email...");
    }
}
