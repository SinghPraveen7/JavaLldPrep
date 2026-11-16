package org.practice.design.practice.warehouse_task_orchestrator;

public class Task {

    private int taskId;
    private int sourceDistance;
    private int tagetDistance;
    private TaskState taskState;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getSourceDistance() {
        return sourceDistance;
    }

    public void setSourceDistance(int sourceDistance) {
        this.sourceDistance = sourceDistance;
    }

    public int getTagetDistance() {
        return tagetDistance;
    }

    public void setTagetDistance(int tagetDistance) {
        this.tagetDistance = tagetDistance;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }
}
