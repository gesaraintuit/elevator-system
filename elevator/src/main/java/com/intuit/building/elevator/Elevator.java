/**
 * 
 */
package com.intuit.building.elevator;

import java.util.PriorityQueue;

import com.intuit.building.elevator.exception.InvalidElevatorRequestException;

/**
 * @author sunparmar
 *
 */
//TODO: possible candidate for abstract class
public interface Elevator {
	void setState(ElevatorState state);
	PriorityQueue<Integer> getDownQueue();
	PriorityQueue<Integer> getUpQueue();
	Integer getCurrentFloor();
	ElevatorState getState();
	void openDoor();
	void holdDoor();
	void closeDoor();
	void moveUp() throws InvalidElevatorRequestException;
	void moveDown() throws InvalidElevatorRequestException;
	boolean isStanding();
	public void buttonPressed( int floor) throws InvalidElevatorRequestException;
	
}
