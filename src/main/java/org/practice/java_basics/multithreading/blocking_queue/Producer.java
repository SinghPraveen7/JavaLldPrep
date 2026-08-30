package org.practice.java_basics.multithreading.blocking_queue;


import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            boolean isPushed = queue.offer(i + 1);
            if (isPushed) System.out.println("Produced Data: " + (i + 1));
            else System.out.println("Queue is full, skipping: " + (i + 1));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
