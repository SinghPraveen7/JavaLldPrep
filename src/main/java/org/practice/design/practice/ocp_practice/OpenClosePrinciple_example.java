package org.practice.design.practice.ocp_practice;

public class OpenClosePrinciple_example {

    public static void main(String[] args) {
        Shape square = new Square(10);
        CalculateArea calculateArea = new CalculateArea();
        double answer = square.calculateArea();
        System.out.println("Area of Square: " + answer);

        Shape rectangle = new Rectangle(10, 5);
        answer = rectangle.calculateArea();
        System.out.println("Area of Rectangle: " + answer);
    }

}

class Square implements Shape {
    double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double calculateArea() {
        return this.length * this.length;
    }
}

class Rectangle implements Shape {
    double length;
    double breath;

    public Rectangle(double length, double breath) {
        this.length = length;
        this.breath = breath;
    }

    @Override
    public double calculateArea() {
        return this.length * this.breath;
    }
}

class Cirle implements Shape {
    double radius;

    public Cirle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 3.14 * this.radius * this.radius;
    }
}

// Bad example for OCP
class CalculateArea {
    public double calculate(Object shape) {
        if (shape instanceof Square) {
            Square square = (Square) shape;
            return square.length * square.length;
        } else if (shape instanceof Rectangle) {
            // Logic for every shape type we need to write if shape type increases
            return 1;
        } else if (shape instanceof Cirle) {
            return 1;
        }
        return 0;
    }
}

interface Shape {
    public double calculateArea();
}
