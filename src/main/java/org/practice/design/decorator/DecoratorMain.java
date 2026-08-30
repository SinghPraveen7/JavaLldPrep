package org.practice.design.decorator;

/**
 * The Decorator Pattern is a structural design pattern used to add new functionality to an object dynamically without changing its existing code.
 * It works by wrapping the original object inside a decorator that implements the same interface.
 * Unlike inheritance, which adds behavior at compile time, decorators add behavior at runtime and help avoid subclass explosion.
 * A classic Java example is the IO stream hierarchy, where BufferedInputStream wraps FileInputStream and DataInputStream can further wrap BufferedInputStream.
 * Decorator follows the Open-Closed Principle and relies on composition rather than inheritance.
 */
public class DecoratorMain {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        System.out.println("Coffee desc: " + coffee.getDescription() + " | Cost: " + coffee.getCost());
        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("Coffee desc: " + milkCoffee.getDescription() + " | Cost: " + milkCoffee.getCost());
        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Coffee desc: " + sugarMilkCoffee.getDescription() + " | Cost: " + sugarMilkCoffee.getCost());
        Coffee creamCoffee = new CreamDecorator(coffee);
        System.out.println("Coffee desc: " + creamCoffee.getDescription() + " | Cost: " + creamCoffee.getCost());
    }
}
