package com.intuit.building.elevator;

import com.intuit.building.elevator.exception.InvalidElevatorRequestException;

import junit.framework.TestCase;

public class ElevatorTest extends TestCase {
	SimpleElevator e = new SimpleElevator(10);
	
	public void testIsStanding() {
		e.setState(ElevatorState.DOWN);
		assertFalse(e.isStanding());
		e.setState(ElevatorState.IDLE);
		assertTrue(e.isStanding());
	}

	public void testGetCurrentFloor() {
		try {
			e.moveUp();
			e.moveUp();
			assertEquals(e.getCurrentFloor(), new Integer(3));
		} catch (InvalidElevatorRequestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void testMoveUp() {
		try {
			e.moveUp();
			e.moveUp();
			assertEquals(e.getCurrentFloor(), new Integer(3));
		} catch (InvalidElevatorRequestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void testMoveDown() {
		try {
			int current = e.getCurrentFloor();
			if(e.getCurrentFloor() > 1){
				e.moveDown();
				e.moveUp();
			} else{
				e.moveUp();
				e.moveDown();
			}
			assertEquals(e.getCurrentFloor(), new Integer(current));
		} catch (InvalidElevatorRequestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	public void testIsValidFloor() {
		e.isValidFloor(0);
		e.isValidFloor(10 +1);
	}

}
