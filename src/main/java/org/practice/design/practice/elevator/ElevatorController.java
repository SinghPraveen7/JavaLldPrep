package org.practice.design.practice.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    List<Elevator> elevatorList;

    ElevatorSelectionStrategy elevatorSelectionStrategy;

    public ElevatorController() {
        this.elevatorList = new ArrayList<>();
        elevatorSelectionStrategy = new NearestElevatorStrategy();
    }

    public void requestElevator(int floor, Direction direction) {
        // find the best elevator
        // add request to elevator request for hall's floor
        Request hallCallRequest = new Request(floor, direction, RequestType.HALL_CALL);
        Elevator elevator = findBestElevator(hallCallRequest);
        elevator.addRequest(hallCallRequest);
    }

    private Elevator findBestElevator(Request hallCallRequest) {
        return elevatorSelectionStrategy.findElevator(hallCallRequest, elevatorList);
    }

    public void steps() {
        for (Elevator elevator: elevatorList) {
            elevator.step();
        }
    }

}
