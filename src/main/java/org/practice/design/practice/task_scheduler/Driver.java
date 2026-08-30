package org.practice.design.practice.task_scheduler;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        TaskScheduler taskScheduler = new TaskScheduler(3);
        System.out.println("Main thread sleeping for 2 seconds");
        Thread.sleep(2000);
        Runnable myTask1 = () -> System.out.println("My Task1 is running!");
        System.out.println("Submitting One task with 2 second delay-");
        taskScheduler.submitOneTimeTask(myTask1, 2000);
        System.out.println("Main thread sleeping for 3 seconds");
        Thread.sleep(3000);
        Runnable myTask2 = () -> System.out.println("My Task2 is running!");
        System.out.println("Submitting Recurring task with 2 second delay and 1 second interval-");
        String recurringTaskId = taskScheduler.submitRecurringTask(myTask2, 2000, 1000);
        System.out.println("Main thread sleeping for 5 seconds");
        Thread.sleep(5000);
        System.out.println("Cancelling recurring task");
        taskScheduler.cancelTask(recurringTaskId);
    }

}
