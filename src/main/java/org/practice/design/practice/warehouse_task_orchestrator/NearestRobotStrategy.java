package org.practice.design.practice.warehouse_task_orchestrator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NearestRobotStrategy implements RobotSelectionStrategy {
    @Override
    public Optional<Robot> selectBestRobot(List<Robot> availableRobots, Task task) {
        return availableRobots.stream()
                .min(Comparator.comparingInt(r -> r.distanceTo(task.getSourceDistance())));
    }
}
