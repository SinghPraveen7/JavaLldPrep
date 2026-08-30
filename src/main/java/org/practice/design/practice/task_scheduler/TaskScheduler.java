package org.practice.design.practice.task_scheduler;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {
    PriorityQueue<ScheduledTask> taskQueue;
    Map<String, ScheduledTask> map;
    ReentrantLock reentrantLock;
    Condition condition;
    List<Thread> threadList;
    public volatile boolean isShutDown;

    public TaskScheduler(int threadPoolSize) {
        this.isShutDown = false;
        this.taskQueue = new PriorityQueue<>();
        this.reentrantLock = new ReentrantLock();
        this.condition = reentrantLock.newCondition();
        this.map = new HashMap<>();
        this.threadList = createPool(threadPoolSize);
    }

    private List<Thread> createPool(int threadPoolSize) {
        List<Thread> pool = new ArrayList<>();
        for (int i = 0; i < threadPoolSize; i++) {
            Thread thread = new Thread(new WorkerThread());
            thread.start();
            pool.add(thread);
        }
        return pool;
    }

    public String submitOneTimeTask(Runnable task, long delay) {
        ScheduledTask scheduledTask = new ScheduledTask(TaskType.ONE_TIME, task, delay, 0);
        return addTask(scheduledTask);
    }

    private String addTask(ScheduledTask scheduledTask) {
        map.put(scheduledTask.getTaskId(), scheduledTask);
        reentrantLock.lock();
        try {
            taskQueue.offer(scheduledTask);
            if (taskQueue.peek() == scheduledTask) {
                condition.signal();
            }
        } finally {
            reentrantLock.unlock();
        }
        return scheduledTask.getTaskId();
    }

    public String submitRecurringTask(Runnable task, long delay, long interval) {
        ScheduledTask scheduledTask = new ScheduledTask(TaskType.RECURRING, task, delay, interval);
        return addTask(scheduledTask);
    }

    public void shutdown() {
        this.isShutDown = true;
        reentrantLock.lock();
        try {
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void cancelTask(String taskId) {
        ScheduledTask scheduledTask = map.get(taskId);
        if (scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

    class WorkerThread implements Runnable {

        @Override
        public void run() {
            while (!isShutDown) {
                reentrantLock.lock();
                ScheduledTask scheduledTask = null;
                try {
                    while (taskQueue.isEmpty() && !isShutDown) {
                        condition.await();
                    }
                    if (isShutDown) {
                        break;
                    }
                    scheduledTask = taskQueue.peek();
                    long executionTime = scheduledTask.getNextExecutionTime();
                    if (System.currentTimeMillis() >= executionTime) {
                        taskQueue.poll();
                    } else {
                        System.out.println("Wait time: " + (executionTime - System.currentTimeMillis()));
                        condition.await(executionTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                        continue;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    reentrantLock.unlock();
                }
                if (scheduledTask != null && !scheduledTask.isCancelled()) {
                    executeTask(scheduledTask);
                } else {
                    if (scheduledTask != null && scheduledTask.isCancelled()) {
                        map.remove(scheduledTask.getTaskId());
                    }
                }
            }
        }

        public void executeTask(ScheduledTask scheduledTask) {
            System.out.println("Executing task...");
            try {
                scheduledTask.getTask().run();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
            if (scheduledTask.getTaskType() == TaskType.RECURRING && !scheduledTask.isCancelled()) {
                scheduledTask.setNextExecutionTime(System.currentTimeMillis() + scheduledTask.getIntervalTime());
                addTask(scheduledTask);
            } else {
                map.remove(scheduledTask.getTaskId());
            }
        }

    }

}

