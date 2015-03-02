package com.intuit.elevator.control;

import com.intuit.building.elevator.floor.Direction;
import com.intuit.elevator.ElevatorState;

public interface Request {
	public int getFloor();
	public ElevatorState getState();
	public Direction getDirection();
}
