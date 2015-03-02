/**
 * 
 */
package com.intuit.elevator;

import java.util.Observable;

import com.intuit.elevator.control.ElevatorRequest;

/**
 * @author sunparmar
 *
 */
public class SimpleElevatorControl extends Observable implements ElevatorControl{

	Elevator e;
	public SimpleElevatorControl(Elevator e){
		this.e = e;
	}

	/**
	 * Notify observer ( controller in this case )
	 */
	public void buttonPressed(int floor){
		notifyObservers(new ElevatorRequest(floor));
	}

	public void buttonPressed(ElevatorState state) {
		notifyObservers(new ElevatorRequest(state));
	}
}
