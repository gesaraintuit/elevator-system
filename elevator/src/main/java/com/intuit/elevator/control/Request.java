package com.intuit.elevator.control;

import com.intuit.building.elevator.floor.Direction;

public interface Request {
	public int getFloor();
	public Direction getDirection();
}
