package org.practice.java_basics.multithreading.producer_consumer;

public class Driver {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Producer producer = new Producer(sharedResource);
        Consumer consumer = new Consumer(sharedResource);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        System.out.println("Start Producer Consumer thread...");
        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {

    SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                resource.produceData(i + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {

    SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                resource.consumeData();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}