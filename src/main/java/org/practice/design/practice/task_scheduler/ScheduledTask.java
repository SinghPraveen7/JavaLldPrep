package org.practice.design.practice.task_scheduler;

public class ScheduledTask implements Comparable<ScheduledTask> {

    private String taskId;
    private Runnable task;

    public void setNextExecutionTime(long nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }

    private long nextExecutionTime;
    private long intervalTime;
    private volatile boolean isCancelled;
    private TaskType taskType;

    public ScheduledTask(TaskType taskType, Runnable task, long delay, long intervalTime) {
        this.taskType = taskType;
        this.isCancelled = false;
        this.task = task;
        this.intervalTime = intervalTime;
        this.nextExecutionTime = System.currentTimeMillis() + delay;
    }

    @Override
    public int compareTo(ScheduledTask scheduledTask) {
        return Long.compare(this.nextExecutionTime, scheduledTask.nextExecutionTime);
    }

    public Runnable getTask() {
        return task;
    }

    public String getTaskId() {
        return taskId;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public TaskType getTaskType() {
        return taskType;
    }


}
