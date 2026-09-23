package org.practice.java_basics.multithreading;

/**
 * four conditions for deadlock
 * All four Coffman conditions must be present:
 * 1. Mutual exclusion — only one thread can own a lock.
 * 2. Hold and wait — thread holds one lock while requesting another.
 * 3. No preemption — another thread can't forcibly take the lock.
 * 4. Circular wait — T1 waits for T2's resource while T2 waits for T1's.
 *
 * Answer for how to avoid deadlock:
 * First, I would establish a consistent global lock ordering so all threads acquire multiple locks in the same order.
 * I'd minimize nested locking and keep critical sections small, especially avoiding external I/O while holding locks.
 * Where appropriate, I'd use ReentrantLock.tryLock() with a timeout rather than waiting indefinitely,
 * or higher-level concurrency utilities to avoid explicit locking. In production, I'd diagnose suspected deadlocks
 * using thread dumps through jstack or jcmd.
 */
public class DeadLock {
    private static final Object RESOURCE_1 = new Object();
    private static final Object RESOURCE_2 = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                synchronized (RESOURCE_1) {
                    System.out.println("Resource 1 lock taken by thread 1...");
                    Thread.sleep(10);
                    System.out.println("Thread 1 is now requesting for Resource 2...");
                    synchronized (RESOURCE_2) {
                        System.out.println("Resource 2 lock taken by thread 1...");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                synchronized (RESOURCE_2) {
                    System.out.println("Resource 2 lock taken by thread 2...");
                    Thread.sleep(10);
                    System.out.println("Thread 2 is now requesting for Resource 1...");
                    synchronized (RESOURCE_1) {
                        System.out.println("Resource 1 lock taken by thread 2...");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        thread1.start();
        thread2.start();
    }
}
