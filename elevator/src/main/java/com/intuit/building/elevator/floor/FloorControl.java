/**
 * 
 */
package com.intuit.building.elevator.floor;

import com.intuit.building.elevator.exception.InvalidElevatorRequestException;


/**
 * @author sunparmar
 *
 */
public interface FloorControl {
	public void buttonPressed(Direction direction) throws InvalidElevatorRequestException;
}
