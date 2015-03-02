/**
 * 
 */
package com.intuit.building.elevator.control;

import java.util.Observer;

import com.intuit.building.elevator.Elevator;

/**
 * @author sunparmar
 *
 */
public interface RequestHandler extends Observer, Runnable {

	void registerElevator(Elevator e);

}
