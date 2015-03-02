/**
 * 
 */
package com.intuit.building.elevator.floor;

import java.util.Observable;

import com.intuit.building.elevator.control.ElevatorRequest;
import com.intuit.building.elevator.exception.InvalidElevatorRequestException;

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
	 * @throws InvalidElevatorRequestException 
	 */
	public void buttonPressed(Direction direction) throws InvalidElevatorRequestException{
		if(direction == null)
			throw new InvalidElevatorRequestException("Direction required at Floor Control");
		setChanged();
		notifyObservers(new ElevatorRequest(floor, direction));
		clearChanged();
	}
}
