package org.practice.design.practice.elevator;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    int TOTAL_FLOOR = 10;

    @Override
    public Elevator findElevator(Request request, List<Elevator> elevatorList) {
        Elevator bestElevator = findMovingTowardsElevator(request, elevatorList);
        if (bestElevator != null) return bestElevator;
        bestElevator = findNearestIdleElevator(request, elevatorList);
        if (bestElevator != null) return bestElevator;
        bestElevator = findNearestElevator(request, elevatorList);
        return bestElevator;
    }

    private Elevator findNearestElevator(Request request, List<Elevator> elevatorList) {
        int floor = request.getFloor();
        int minDistance = Integer.MAX_VALUE;
        Elevator bestElevator = null;
        for (Elevator elevator: elevatorList) {
            if ((Math.abs(elevator.getCurrentFloor() - floor) + TOTAL_FLOOR) < minDistance) {
                minDistance = Math.abs(elevator.getCurrentFloor() - floor) + TOTAL_FLOOR;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }

    private Elevator findNearestIdleElevator(Request request, List<Elevator> elevatorList) {
        int floor = request.getFloor();
        int minDistance = Integer.MAX_VALUE;
        Elevator bestElevator = null;
        for (Elevator elevator: elevatorList) {
            if (elevator.getDirection() != Direction.IDLE) continue;
            if (Math.abs(elevator.getCurrentFloor() - floor) < minDistance) {
                minDistance = Math.abs(elevator.getCurrentFloor() - floor);
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }

    private Elevator findMovingTowardsElevator(Request request, List<Elevator> elevatorList) {
        int floor = request.getFloor();
        Direction direction = request.getDirection();
        int minDistance = Integer.MAX_VALUE;
        Elevator bestElevator = null;
        for (Elevator elevator: elevatorList) {
            if (elevator.getDirection() != direction) continue;
            if ((direction == Direction.UP && elevator.getCurrentFloor() > floor) ||
                    (direction == Direction.DOWN && elevator.getCurrentFloor() < floor)) continue;
            if (Math.abs(elevator.getCurrentFloor() - floor) < minDistance) {
                minDistance = Math.abs(elevator.getCurrentFloor() - floor);
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}
