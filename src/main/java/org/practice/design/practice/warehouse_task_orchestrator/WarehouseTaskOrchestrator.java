package org.practice.design.practice.warehouse_task_orchestrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseTaskOrchestrator {
    private ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
    private List<Robot> robots = new ArrayList<>();
    private ConcurrentHashMap<Integer, ReentrantLock> robotLocks = new ConcurrentHashMap<>();
    private RobotSelectionStrategy robotSelectionStrategy;

    public WarehouseTaskOrchestrator(RobotSelectionStrategy strategy, List<Robot> availableRobots) {
        this.robotSelectionStrategy = strategy;
        this.robots = availableRobots;
    }

    boolean assignTaskToRobot(int taskId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            System.out.println("No task is found with: " + taskId + " taskId");
            return false;
        }
        Optional<Robot> optionalRobot = robotSelectionStrategy.selectBestRobot(robots, task);
        if (optionalRobot.isEmpty()) {
            System.out.println("No suitable robot found!");
            return false;
        }
        Robot robot = optionalRobot.get();
        ReentrantLock robotLock = getRobotLock(robot);
        if (robotLock.tryLock()) {
            try {
                if (robot.isAvailable()) {
                    robot.setAvailable(false);
                    task.setTaskState(TaskState.ASSIGNED);
                    robot.assignTask(task);
                    return true;
                }
            } finally {
                robotLock.unlock();
            }
        }
        return false;
    }

    private ReentrantLock getRobotLock(Robot robot) {
        if (!robotLocks.containsKey(robot.getRobotId())) {
            robotLocks.put(robot.getRobotId(), new ReentrantLock());
        }
        return robotLocks.get(robot.getRobotId());
    }
}
