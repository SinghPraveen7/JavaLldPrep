package org.practice.java_basics.multithreading.virtual_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Traditionally, every Java Thread was a platform thread—a thin wrapper tied 1:1 to a heavy OS thread.
 * Creating thousands of them ate up gigabytes of memory and slowed down the CPU.
 * Virtual threads break that 1:1 link:
 * 1. Mounting. The JVM schedules a virtual thread to run on top of a real platform thread (called a carrier thread).
 * 2. Unmounting (Parking). When your code hits a waiting operation—like a database query, network call, or Thread.sleep()—
 * the JVM pauses the virtual thread, saves its state in regular heap memory, and unmounts it.
 * 3. Reusing. The freed carrier thread immediately picks up another virtual thread. When the network or database call finishes,
 * the JVM mounts the waiting virtual thread back onto an available carrier thread to resume work.
 */
public class VirtualThreadCompare {
    public static void main(String[] args) {
        // We are running 1000 tasks assuming each task is taking 1 sec to complete
        int taskCount = 1000;
        long startTime = System.currentTimeMillis();
        // can't create 1000 platform threads, as it may choke our OS capacity
        // if we create large number of platform thread, possible execption like- Execption in thread "main" java.lang.OutOfMemoryError: 
        // unable to create native thread: possibly out of memory or process/resource limits reached
        ExecutorService executor = Executors.newFixedThreadPool(100);
        try {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    Thread.sleep(1000);
                    return null;
                });
            }
        } finally {
            executor.close();
        }
        System.out.println("Time taken by platform threads to execute 1000 " +
                "tasks: " + (System.currentTimeMillis() - startTime) + " ms.");
        startTime = System.currentTimeMillis();
        // Can create millions of virtual thread at a time as it is managed by JVM only
        var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
        try {
            for (int i = 0; i < taskCount; i++) {
                virtualExecutor.submit(() -> {
                    Thread.sleep(1000);
                    return null;
                });
            }
        } finally {
            virtualExecutor.close();
        }
        System.out.println("Time taken by virtual threads to execute 1000 " +
                "tasks: " + (System.currentTimeMillis() - startTime) + " ms.");

    }
}
