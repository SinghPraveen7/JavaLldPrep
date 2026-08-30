package org.practice.design.decorator;

public class CreamDecorator extends CoffeeDecorator {
    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 50;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Cream";
    }
}
