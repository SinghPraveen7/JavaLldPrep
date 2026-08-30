package org.practice.design.practice.elevator;

import java.util.Set;
import java.util.TreeSet;

public class Elevator {

    private static final int MAX_FLOOR = 10;
    int id;
    int currentFloor;
    Direction direction;
    Set<Request> requests;

    public Elevator() {
        requests = new TreeSet<>();
    }

    // This method can be directly called inside elevator also - destination requests
    // This is called to add hall call requests and destination requests
    public void addRequest(Request request) {
        requests.add(request);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    // This can also be strategy - FIFO or SCAN
    // Most of the real world Elevator system follows SCAN algorithm
    // Here Implementing SCAN
    public void step() {
        // Step 1: request is empty
        if (requests.isEmpty()) {
            this.direction = Direction.IDLE;
            return;
        }
        // Step 2: Elevator was idle and now got few requests
        if (this.direction == Direction.IDLE) {
            Integer nearestFloorRequest = findNearestRequest(requests);
            this.direction = this.currentFloor > nearestFloorRequest ? Direction.DOWN : Direction.UP;
            return;
        }
        // Step 3: Elevator is moving and request is not empty, check if need to stop at current floor
        Request hallRequest = new Request(this.currentFloor, this.direction, RequestType.HALL_CALL);
        Request destinationRequest = new Request(this.currentFloor, this.direction, RequestType.DESTINATION);
        if (requests.contains(hallRequest) || requests.contains(destinationRequest)) {
            stopTheElevator(this.currentFloor);
            requests.remove(hallRequest);
            requests.remove(destinationRequest);
            return;
        }
        // Step 4: No request ahead, reverse the direction
        if (!requestAhead()) {
            this.direction = this.direction == Direction.UP ? Direction.DOWN: Direction.UP;
        }
        // Step 5: move the elevator
        if (this.direction == Direction.UP) {
            this.currentFloor = this.currentFloor + 1;
        } else if (this.direction == Direction.DOWN) {
            this.currentFloor = this.currentFloor - 1;
        }

    }

    private boolean requestAhead() {
        if (!requests.isEmpty()) {
            if (this.direction == Direction.DOWN) {
                return containsLowerFloorRequests(this.currentFloor);
            } else if (this.direction == Direction.UP) {
                return containsHigherFloorRequests(this.currentFloor);
            }
        }
        return false;
    }

    private boolean containsHigherFloorRequests(int currentFloor) {
        for (int i = currentFloor + 1; i < MAX_FLOOR; i++) {
            Request hallRequest = new Request(i, this.direction, RequestType.HALL_CALL);
            Request destinationRequest = new Request(i, this.direction, RequestType.DESTINATION);
            if (requests.contains(hallRequest) || requests.contains(destinationRequest)) return true;
        }
        return false;
    }

    private boolean containsLowerFloorRequests(int currentFloor) {
        for (int i = currentFloor - 1; i <= 0; i--) {
            Request hallRequest = new Request(i, this.direction, RequestType.HALL_CALL);
            Request destinationRequest = new Request(i, this.direction, RequestType.DESTINATION);
            if (requests.contains(hallRequest) || requests.contains(destinationRequest)) return true;
        }
        return false;
    }

    private void stopTheElevator(int floor) {
        System.out.println("Elevator is stopped at " + floor + " floor.");
    }

    private Integer findNearestRequest(Set<Request> requests) {
        int distance = Integer.MAX_VALUE;
        int nearestFloor = 0;
        for (Request i: requests) {
            if (Math.abs(i.getFloor() - this.currentFloor) < distance) {
                distance = Math.abs(i.getFloor() - this.currentFloor);
                nearestFloor = i.getFloor();
            }
        }
        return nearestFloor;
    }
}
