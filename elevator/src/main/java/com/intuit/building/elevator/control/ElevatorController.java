package com.intuit.building.elevator.control;

import com.intuit.building.elevator.ElevatorState;
import com.intuit.building.elevator.Elevator;
import com.intuit.building.elevator.floor.Direction;

public interface ElevatorController {
	
	public void move(Elevator e);
	public void moveUp(Elevator e);
	public void moveDown(Elevator e);
	public void updateState(Elevator e, ElevatorState state);
	public void addFloorToDestination(Elevator e, int floor, Direction direction);
}
