package org.practice.java_basics.multithreading.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    BlockingQueue<Integer> queue;
    String name;

    public Consumer(BlockingQueue<Integer> queue, String name) {
        this.queue = queue;
        this.name = name;
    }
    @Override
    public void run() {
        while (true) {
            try {
                int data = queue.take();
                System.out.println("Consumed Data by " + name + ": " + data);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
