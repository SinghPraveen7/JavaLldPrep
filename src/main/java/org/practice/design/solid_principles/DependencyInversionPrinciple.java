package org.practice.design.solid_principles;

/**
 * The Dependency Inversion Principle (DIP) states that “high-level components should not
 * depend on low-level components directly; instead, they should depend on abstractions.”
 * In other words, classes should depend on interfaces rather than concrete classes. Additionally, abstractions should
 * not depend on details; details should depend on abstractions.
 *
 */
public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        System.out.println("Dependency inversion Principle states that a high level module should not " +
                "dependent on low level module instead both should depend on abstraction");
        WiredMouse wiredMouse = new WiredMouse();
        BluetoothMouse bluetoothMouse = new BluetoothMouse();
        System.out.println("##############################################################");
        System.out.println("Bad example of DIP!");
        Laptop wiredLaptop = new Laptop(wiredMouse);
        System.out.println("##############################################################");
        System.out.println("Good example of DIP!");
        GoodLaptop goodWiredLaptop = new GoodLaptop(wiredMouse);
        GoodLaptop goodBluetoothLaptop = new GoodLaptop(bluetoothMouse);
    }

    interface Mouse {
        public void connect();
    }

    static class WiredMouse implements Mouse {
        public WiredMouse() {
            System.out.println("Wired Mouse!");
        }

        @Override
        public void connect() {
            System.out.println("Mouse connected via wire!");
        }
    }

    static class BluetoothMouse implements Mouse {
        public BluetoothMouse() {
            System.out.println("Bluetooth Mouse!");
        }

        @Override
        public void connect() {
            System.out.println("Mouse connected via bluetooth!");
        }
    }

    // Laptop Directly dependent on WiredMouse and BluetoothMouse class (Low level module)
    static class Laptop {
        WiredMouse wiredMouse;

        public Laptop(WiredMouse wiredMouse) {
            this.wiredMouse = wiredMouse;
            System.out.println("Laptop is ready!");
        }
    }

    // Laptop dependent on Mouse Interface (High level module)
    static class GoodLaptop {

        Mouse mouse;

        public GoodLaptop(Mouse mouse) {
            this.mouse = mouse;
            mouse.connect();
            System.out.println("Laptop is ready!");
        }
    }
}
