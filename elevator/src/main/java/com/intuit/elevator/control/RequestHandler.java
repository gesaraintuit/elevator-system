/**
 * 
 */
package com.intuit.elevator.control;

import java.util.Observer;

import com.intuit.elevator.Elevator;

/**
 * @author sunparmar
 *
 */
public interface RequestHandler extends Observer, Runnable {

	void registerElevator(Elevator e);

}
