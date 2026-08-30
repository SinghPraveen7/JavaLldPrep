package org.practice.java_basics.multithreading.implementation;

/**
 * Process = independent program.
 * Thread = lightweight execution unit inside process.
 * Threads share heap memory.
 * Each thread has its own stack.
 * start() creates a new thread.
 * run() is just a normal method call.
 * Prefer Runnable over extending Thread.
 * OS scheduler decides execution order.
 */

public class ThreadClassImpl {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread Class Example!");
        MyThread myThread = new MyThread();
        YourThread yourThread = new YourThread();
        System.out.println("Starting ...");
        myThread.start();
        yourThread.start();
        Thread.sleep(1); // Comment and Uncomment to see execution behavior
        System.out.println("End ...");
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("MyThread is running!");
    }
}

class YourThread extends Thread {

    @Override
    public void run() {
        System.out.println("YourThread is running!");
    }
}
