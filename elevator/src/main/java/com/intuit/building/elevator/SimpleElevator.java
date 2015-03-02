/**
 * 
 */
package com.intuit.building.elevator;

import java.util.Collections;
import java.util.Observable;
import java.util.PriorityQueue;

import com.intuit.building.elevator.control.ElevatorRequest;
import com.intuit.building.elevator.exception.InvalidElevatorRequestException;

/**
 * @author sunparmar
 *
 */
public class SimpleElevator extends Observable implements Elevator {

	public static enum DoorState {
		OPEN, CLOSE, HOLD
	};

	int currentFloor;
	PriorityQueue<Integer> upQueue;
	PriorityQueue<Integer> downQueue;
	ElevatorState state;
	int lowestFloor;
	int highestFloor;
	Door doors;

	public SimpleElevator(int buildingSize) {
		this.state = ElevatorState.IDLE;
		upQueue = new PriorityQueue<Integer>(buildingSize);
		downQueue = new PriorityQueue<Integer>(buildingSize,
				Collections.reverseOrder());
		lowestFloor = 1;
		highestFloor = buildingSize;
		currentFloor = 1;
		doors = new Door();
	}

	class Door {
		DoorState state;

		public Door() {
			state = DoorState.CLOSE;
		}

		public void open() {
			state = DoorState.OPEN;
		}

		public void close() {
			state = DoorState.CLOSE;
		}

		public void hold() {
			state = DoorState.HOLD;
		}

		@Override
		public String toString() {
			return "Door [state=" + state + "]";
		}

	}

	public void maintain() {
		state = ElevatorState.MAINTAINANCE;
	}

	public void completeMaintainance() {
		state = ElevatorState.IDLE;
	}

	void logStatus() {
		System.out.println(this);
	}

	/**
	 * If Elevator is moving or not
	 * 
	 * @return
	 */
	public boolean isStanding() {
		return state == ElevatorState.IDLE;
	}

	@Override
	public String toString() {
		return "Elevator [currentFloor=" + currentFloor + ", upQueue="
				+ upQueue + ", downQueue=" + downQueue + ", state=" + state
				+ ", " + doors + "]";
	}

	/**
	 * Notify observer ( controller in this case )
	 */
	public void buttonPressed(int floor) {
		this.setChanged();
		notifyObservers(new ElevatorRequest(floor));
		this.clearChanged();
	}

	public PriorityQueue<Integer> getDownQueue() {
		return downQueue;
	}

	public PriorityQueue<Integer> getUpQueue() {
		return upQueue;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void moveUp() throws InvalidElevatorRequestException {
		this.setCurrentFloor(getCurrentFloor() + 1);
	}

	private void setCurrentFloor(int floor) throws InvalidElevatorRequestException {
		if(isValidFloor(floor))
			this.currentFloor = floor;
		else
			throw new InvalidElevatorRequestException(floor,"out of range");
	}

	public void moveDown() {
		this.currentFloor = getCurrentFloor() - 1;
	}

	public ElevatorState getState() {
		return state;
	}

	public void setState(ElevatorState state) {
		this.state = state;
	}

	public void openDoor() {
		this.doors.open();

	}

	public void holdDoor() {
		this.doors.hold();
	}

	public void closeDoor() {
		this.doors.close();
	}

	public boolean isValidFloor(int floor) {
		if (floor < lowestFloor || floor > highestFloor)
			return false;
		else
			return true;
	}
}
