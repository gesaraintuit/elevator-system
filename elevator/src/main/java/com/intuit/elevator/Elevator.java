/**
 * 
 */
package com.intuit.elevator;

/**
 * @author sunparmar
 *
 */
public interface Elevator extends Runnable{
	void addFloorToDestination(int i);

	void updateState(ElevatorState state);
}
