package org.practice.design.practice.warehouse_task_orchestrator;

public class Robot {

    public int getRobotId() {
        return robotId;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    private int robotId;
    private boolean isAvailable;
    private Task task;
    private int currentLocation;
    void assignTask(Task task) {
        this.task = task;
    }

    public int distanceTo(int sourceDistance) {
        return Math.abs(this.currentLocation - sourceDistance);
    }
}
