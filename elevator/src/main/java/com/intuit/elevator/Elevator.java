/**
 * 
 */
package com.intuit.elevator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.PriorityQueue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * @author sunparmar
 *
 */
public class Elevator extends Observable {
	public static enum ElevatorState {
		UP, DOWN, STAND, MAINTAINANCE
	};

	public static enum DoorState {
		OPEN, CLOSE, HOLD
	};

	int currentFloor;
	PriorityQueue<Integer> upQueue;
	PriorityQueue<Integer> downQueue;
	ElevatorState state;
	int lowestFloor;
	int highestFloor;

	class Door {
		DoorState state;

		public void open() {

		}

		public void close() {

		}

		public void hold(Integer seconds) {

		}
	}

	Door doors;

	public void maintain() {
		state = ElevatorState.MAINTAINANCE;
	}

	public void completeMaintainance() {
		state = ElevatorState.STAND;
	}

	public void alarm() {
		if (isValid(currentFloor)) {

		}
	}

	private boolean isValid(int currentFloor) {
		if (currentFloor >= lowestFloor && currentFloor <= highestFloor)
			return true;
		else
			return false;
	}

	public void moveUp() throws InvalidElevatorRequestException {
		if (upQueue== null|| upQueue.isEmpty() || currentFloor == highestFloor) {
			this.state = ElevatorState.STAND;
		} else if (downQueue.peek() == currentFloor) {
			this.doors.open();
			this.doors.hold(10);
			this.doors.close();
		} else {
			this.currentFloor++;
			moveDown();
		}
	}

	public void moveDown() throws InvalidElevatorRequestException {
		if (downQueue == null || downQueue.isEmpty()  ||currentFloor == lowestFloor) {
			this.state = ElevatorState.STAND;
		} else if (downQueue.peek() == currentFloor) {
			this.doors.open();
			this.doors.hold(10);
			this.doors.close();
		} else {
			this.currentFloor--;
			moveDown();
		}
	}

	public void addFloorToDestination(int floor){
	}
}
