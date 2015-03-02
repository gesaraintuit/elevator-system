package com.intuit.building.elevator.control;

import com.intuit.building.elevator.ElevatorState;
import com.intuit.building.elevator.Elevator;

public interface ElevatorController {
	
	public void move(Elevator e);
	public void moveUp(Elevator e);
	public void moveDown(Elevator e);
	public void addFloorToDestination(Elevator e, int floor);
	public void updateState(Elevator e, ElevatorState state);
	
}
