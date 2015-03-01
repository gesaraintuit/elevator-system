package com.intuit.elevator.control;


public class ElevatorRequest implements Request {
	int floor;
	Direction direction;
	public ElevatorRequest(int floor, Direction direction){
		this.floor = floor;
		this.direction = direction;
	}
	public int getFloor() {
		return floor;
	}
	public Direction getDirection() {
		return direction;
	}
}
