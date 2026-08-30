package org.practice.java_basics.multithreading.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 1 Producer, 3 Consumers example using Blocking Queue
 */
public class Driver {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(8);
        Producer producer = new Producer(queue);
        Consumer consumer1 = new Consumer(queue, "consumer1");
        Consumer consumer2 = new Consumer(queue, "consumer2");
        Consumer consumer3 = new Consumer(queue, "consumer3");
        Thread producerThread = new Thread(producer);
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);
        Thread consumerThread3 = new Thread(consumer3);
        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
    }
}
