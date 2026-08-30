package org.practice.design.observer;

public class PaytmUser implements Observer {

    String name;

    PaytmUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(msg);
    }

    @Override
    public String getName() {
        return name;
    }
}
