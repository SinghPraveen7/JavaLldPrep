package org.practice.java_basics.multithreading;

// This question asked in Mobikqwik 1st round of interview
public class PatternPrinter {

    public static void main(String[] args) {
        AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                alphabetPrinter.printA();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                alphabetPrinter.printB();
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                alphabetPrinter.printC();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

    static class AlphabetPrinter {
        boolean turnA;
        boolean turnB;
        boolean turnC;

        public AlphabetPrinter() {
            turnA = true;
            turnB = false;
            turnC = false;
        }

        public synchronized void printA() {
            while (true) {
                try {
                    while (!turnA) {
                        wait();
                    }
                    System.out.print("A");
                    turnA = false;
                    turnB = true;
                    Thread.sleep(100);
                    notifyAll();
                } catch (InterruptedException e) {

                }
            }
        }

        public synchronized void printB() {
            while (true) {
                try {
                    while (!turnB) {
                        wait();
                    }
                    System.out.print("B");
                    turnB = false;
                    turnC = true;
                    Thread.sleep(100);
                    notifyAll();
                } catch (InterruptedException e) {

                }
            }
        }

        public synchronized void printC() {
            while (true) {
                try {
                    while (!turnC) {
                        wait();
                    }
                    System.out.print("C");
                    turnC = false;
                    turnA = true;
                    Thread.sleep(100);
                    notifyAll();
                } catch (InterruptedException e) {

                }
            }
        }

    }

}
