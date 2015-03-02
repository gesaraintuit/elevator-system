/**
 * 
 */
package com.intuit.elevator;

import java.util.PriorityQueue;

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
	void moveUp();
	void moveDown() ;
	boolean isStanding();
}
