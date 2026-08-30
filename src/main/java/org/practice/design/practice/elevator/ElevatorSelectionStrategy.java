package org.practice.design.practice.elevator;

import java.util.List;

public interface ElevatorSelectionStrategy {

    Elevator findElevator(Request request, List<Elevator> elevatorList);

}
