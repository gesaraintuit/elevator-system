/**
 * 
 */
package com.intuit.elevator;


/**
 * @author sunparmar
 *
 */
public interface ElevatorControl {
	public void buttonPressed(int floor);
	public void buttonPressed(ElevatorState state);
}
