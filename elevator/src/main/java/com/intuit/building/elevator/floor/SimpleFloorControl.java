/**
 * 
 */
package com.intuit.building.elevator.floor;

import java.util.Observable;

import com.intuit.elevator.control.ElevatorRequest;

/**
 * @author sunparmar
 *
 */
public class SimpleFloorControl extends Observable implements FloorControl{

	int floor;
	public SimpleFloorControl(int floor){
		this.floor = floor;
	}

	/**
	 * Notify observer ( controller in this case )
	 */
	public void buttonPressed(Direction direction){
		setChanged();
		notifyObservers(new ElevatorRequest(floor, direction));
		clearChanged();
	}
}
