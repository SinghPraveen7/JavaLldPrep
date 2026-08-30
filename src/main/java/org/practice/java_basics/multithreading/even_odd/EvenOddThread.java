package org.practice.java_basics.multithreading.even_odd;


public class EvenOddThread {

    public static void main(String[] args) {

        NumberPrinter numberPrinter = new NumberPrinter(20);
        Thread odd = new Thread(() -> {
            numberPrinter.oddPrinter();
        });
        Thread even = new Thread(() -> {
            numberPrinter.evenPrinter();
        });
        odd.start();
        even.start();
    }
}

class NumberPrinter {

    int max;
    boolean isOddTurn;

    public NumberPrinter(int max) {
        this.max = max;
        this.isOddTurn = true;
    }

    public synchronized void evenPrinter() {
        for (int i = 2; i <= max; i += 2) {
            try {
                while (isOddTurn) {
                    wait();
                }
                System.out.println("Even thread printing: " + i);
                isOddTurn = true;
                notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public synchronized void oddPrinter() {
        for (int i = 1; i <= max; i += 2) {
            try {
                while (!isOddTurn) {
                    wait();
                }
                System.out.println("Odd thread printing: " + i);
                isOddTurn = false;
                notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}