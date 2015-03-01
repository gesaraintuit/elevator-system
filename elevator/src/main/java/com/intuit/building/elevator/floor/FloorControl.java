/**
 * 
 */
package com.intuit.building.elevator.floor;

import java.util.Observable;
import java.util.Observer;

import com.intuit.elevator.control.Direction;
import com.intuit.elevator.control.ElevatorRequest;

/**
 * @author sunparmar
 *
 */
public class FloorControl extends Observable{

	int floor;
	public FloorControl(int floor, Observer o){
		this.floor = floor;
		addObserver(o);
	}

	
	public void buttonPressed(int floor, Direction direction){
		notifyObservers(new ElevatorRequest(floor, direction));
	}
}
