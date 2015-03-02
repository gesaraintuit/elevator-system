package com.intuit.elevator.control;

import com.intuit.building.elevator.floor.Direction;
import com.intuit.elevator.ElevatorState;


public class ElevatorRequest implements Request {
	private int floor;
	private Direction direction;
	private ElevatorState state;
	public ElevatorRequest(int floor, Direction direction){
		this.floor = floor;
		this.direction = direction;
	}
	public ElevatorRequest(int floor) {
		this.floor = floor;
		this.direction = null;
	}
	public ElevatorRequest(ElevatorState state) {
		this.floor = -1;
		this.direction = null;
		this.state= state;
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
	/**
	 * If available returns the state requested
	 */
	public ElevatorState getState() {
		return state;
	}
}
