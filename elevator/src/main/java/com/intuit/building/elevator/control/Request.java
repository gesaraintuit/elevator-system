package com.intuit.building.elevator.control;

import com.intuit.building.elevator.ElevatorState;
import com.intuit.building.elevator.floor.Direction;

public interface Request {
	public int getFloor();
	public ElevatorState getState();
	public Direction getDirection();
}
