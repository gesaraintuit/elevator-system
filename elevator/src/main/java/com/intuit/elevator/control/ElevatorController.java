package com.intuit.elevator.control;

import com.intuit.elevator.Elevator;
import com.intuit.elevator.ElevatorState;

public interface ElevatorController {
	
	public void move(Elevator e);
	public void moveUp(Elevator e);
	public void moveDown(Elevator e);
	public void addFloorToDestination(Elevator e, int floor);
	public void updateState(Elevator e, ElevatorState state);
	
}
