package org.practice.design.solid_principles;

/**
 * Liskov Substitution Principle(LSP) states that the "objects of a superclass should be
 * replaceable with objects of its subclasses without breaking the application."
 * That means, if class B is a subtype of class A, then we should be able to replace objects of A with B without
 * breaking the behaviour of the program. Subclass should extend the capability of the parent class, not narrow it
 * down.
 *
 * LSP is vital because it upholds the integrity of your class hierarchy, ensuring that extending functionality or
 * creating new subclasses won’t disrupt the program’s existing behavior. It keeps your code clean, modular, and safe
 * to evolve.
 *
 */
public class LiskovSubstitutionPrinciple {

    public static void main(String[] args) {
        System.out.println("Liskov substitution principle states that Object of parent classes should be " +
                "replaceable with the objects of sub-classes, without breaking the application. " +
                "A child should not norrow down the capabilities of parent class.");
        System.out.println("##############################################################");
        System.out.println("Bad example of LSP!");
        Bike motorcycle = new Motorcycle();
        motorcycle.turnOnEngine();
        motorcycle.move();
        Bike bicycle = new Bicycle();
        try {
            bicycle.turnOnEngine();
        } catch (AssertionError ex) {
            System.out.println("Error is: " + ex.getMessage());
        }
        bicycle.move();
        System.out.println("##############################################################");
        System.out.println("Good example of LSP!");
        Motorcycle2 motorcycle2 = new Motorcycle2();
        motorcycle2.turnOnEngine();
        motorcycle2.move();
        Bicycle2 bicycle2 = new Bicycle2();
        bicycle2.move();

    }

    static abstract class Bike {
        abstract void turnOnEngine();
        abstract void move();
    }

    static class Motorcycle extends Bike {

        @Override
        void turnOnEngine() {
            System.out.println("Engine is on!");
        }

        @Override
        void move() {
            System.out.println("Motorcycle is moving...");
        }
    }

    static class Bicycle extends Bike {

        @Override
        void turnOnEngine() {
            throw new AssertionError("Bicycle don't have engine!");
        }

        @Override
        void move() {
            System.out.println("Bicycle is moving...");
        }
    }

    interface GoodBike {
        public void move();
    }

    interface Engine {
        public void turnOnEngine();
    }

    static class Motorcycle2 implements GoodBike, Engine {

        @Override
        public void turnOnEngine() {
            System.out.println("Motorcycle engine is on!");
        }

        @Override
        public void move() {
            System.out.println("Motorcycle is moving...");
        }
    }

    static class Bicycle2 implements GoodBike {

        @Override
        public void move() {
            System.out.println("Bicycle is moving...");
        }
    }

}
