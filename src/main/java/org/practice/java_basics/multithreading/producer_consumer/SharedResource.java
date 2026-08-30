package org.practice.java_basics.multithreading.producer_consumer;

public class SharedResource {

    int data;
    boolean isAvailable;

    public SharedResource() {
        data = -1; // -1 represents no data state
        isAvailable = false;
    }

    public synchronized void produceData(int data) throws InterruptedException {
        while (isAvailable) {
            wait();
        }
        this.data = data;
        isAvailable = true;
        System.out.println("Produced data: " + data);
        Thread.sleep(1000);
        notify();
    }

    public synchronized void consumeData() throws InterruptedException {
        while (!isAvailable) {
            wait();
        }
        System.out.println("Consumed data: " + data);
        this.data = -1;
        isAvailable = false;
        Thread.sleep(500);
        notify();
    }
}
