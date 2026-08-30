package org.practice.design.observer;

public class PhonepeUser implements Observer {

    String name;
    PhonepeUser(String name) {
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
