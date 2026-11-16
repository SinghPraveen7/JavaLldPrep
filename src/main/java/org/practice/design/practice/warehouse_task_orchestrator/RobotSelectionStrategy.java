package org.practice.design.practice.warehouse_task_orchestrator;

import java.util.List;
import java.util.Optional;

public interface RobotSelectionStrategy {
    Optional<Robot> selectBestRobot(List<Robot> availableRobots, Task task);
}
