package org.practice.design.solid_principles;

/**
 * Interface Segregation Principle (ISP) states that "clients should not be forced to depend on
 * interfaces they don't use. Instead of having one large interface with many methods, it's
 * better to have multiple smaller, focused interfaces."
 *
 * The Interface Segregation Principle (ISP) helps maintain focused classes by ensuring they only implement the
 * methods they need. To identify potential violations of the ISP, look for indicators such as low cohesion, large or
 * “fat” interfaces, empty methods, and challenging testing scenarios. By recognizing these signs early, you can
 * refactor your code to uphold a modular and flexible design.
 *
 */
public class InterfaceSegregationPrinciple {

    public static void main(String[] args) {

        System.out.println("Interface segregation principle states that Interfaces should be " +
                "small instead of one fat interface, so classes don't need to implement unnecessary methods.");
        System.out.println("##############################################################");
        System.out.println("Bad example of ISP!");
        Bike motorcycle = new Motorcycle();
        motorcycle.turnOnEngine();
        motorcycle.move();
        motorcycle.stop();
        motorcycle.turnOffEngine();
        Bike bicycle = new Bicycle();
        try {
            bicycle.turnOnEngine();
        } catch (AssertionError error) {
            System.out.println("Error is: " + error.getMessage());
        }
        bicycle.move();
        bicycle.stop();
        try {
            bicycle.turnOffEngine();
        } catch (AssertionError error) {
            System.out.println("Error is: " + error.getMessage());
        }
        System.out.println("##############################################################");
        System.out.println("Good example of ISP!");
        GoodMotorcycle goodMotorcycle = new GoodMotorcycle();
        goodMotorcycle.turnOnEngine();
        goodMotorcycle.move();
        goodMotorcycle.stop();
        goodMotorcycle.turnOffEngine();
        GoodBicycle goodBicycle = new GoodBicycle();
        goodBicycle.move();
        goodBicycle.stop();
    }

    interface Bike {
        public void turnOnEngine();

        public void move();

        public void stop();

        public void turnOffEngine();
    }

    static class Motorcycle implements Bike {

        @Override
        public void turnOnEngine() {
            System.out.println("Motorcycle engine on!");
        }

        @Override
        public void move() {
            System.out.println("Motorcycle moving...");
        }

        @Override
        public void stop() {
            System.out.println("Motorcycle stopped!");
        }

        @Override
        public void turnOffEngine() {
            System.out.println("Motorcycle engine off!");
        }
    }

    // Here Bike class need to implement unnecessary methods - turnOnEngine() and turnOffEngine()
    static class Bicycle implements Bike {

        @Override
        public void turnOnEngine() {
            throw new AssertionError("Bicycle don't have Engine!");
        }

        @Override
        public void move() {
            System.out.println("Bicycle is moving...");
        }

        @Override
        public void stop() {
            System.out.println("Bicycle stopped!");
        }

        @Override
        public void turnOffEngine() {
            throw new AssertionError("Bicycle don't have Engine!");
        }
    }

    // One fat interface divided into small interfaces
    interface GoodBike {
        public void move();

        public void stop();
    }

    interface Engine {
        public void turnOnEngine();

        public void turnOffEngine();
    }

    static class GoodMotorcycle implements GoodBike, Engine {

        @Override
        public void turnOnEngine() {
            System.out.println("Motorcycle engine on!");
        }

        @Override
        public void move() {
            System.out.println("Motorcycle moving...");
        }

        @Override
        public void stop() {
            System.out.println("Motorcycle stopped!");
        }

        @Override
        public void turnOffEngine() {
            System.out.println("Motorcycle engine off!");
        }
    }

    // Don't need to implement unnecessary methods now
    static class GoodBicycle implements GoodBike {

        @Override
        public void move() {
            System.out.println("Bicycle is moving...");
        }

        @Override
        public void stop() {
            System.out.println("Bicycle stopped!");
        }

    }
}
