/**
 * 
 */
package com.intuit.elevator.exception;

/**
 * @author sunparmar
 *
 */
public class InvalidElevatorRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2451336276471920348L;

	public InvalidElevatorRequestException(int floor) {
		super("Invalid floor "+floor+" requested.");
	}
	public InvalidElevatorRequestException(String message){
		super(message);
	}
}
