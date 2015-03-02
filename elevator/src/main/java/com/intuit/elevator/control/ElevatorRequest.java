package com.intuit.elevator.control;

import com.intuit.building.elevator.floor.Direction;


public class ElevatorRequest implements Request {
	int floor;
	Direction direction;
	public ElevatorRequest(int floor, Direction direction){
		this.floor = floor;
		this.direction = direction;
	}
	public ElevatorRequest(int floor) {
		this.floor = floor;
		this.direction = null;
	}
	/**
	 * returns floor requested
	 */
	public int getFloor() {
		return floor;
	}
	/**
	 * If available returns direction requested
	 */
	public Direction getDirection() {
		return direction;
	}
}
