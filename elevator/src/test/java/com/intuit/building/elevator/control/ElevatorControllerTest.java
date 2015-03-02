package com.intuit.building.elevator.control;

import com.intuit.building.elevator.ElevatorState;
import com.intuit.building.elevator.SimpleElevator;
import com.intuit.building.elevator.floor.Direction;

import junit.framework.TestCase;

public class ElevatorControllerTest extends TestCase {
	SimpleElevator e;
	SimpleElevatorController ec;
	protected void setUp() throws Exception {
		super.setUp();
		e = new SimpleElevator(10);
		ec = new SimpleElevatorController(false);
	}

	public void testMove() throws InterruptedException {
		ec.addFloorToDestination(e, 2, null);
		ec.move(e);
		Thread.sleep(3000);
		assertEquals(e.getCurrentFloor(),new Integer(2));
	}

	public void testMoveUp() throws InterruptedException {
		ec.addFloorToDestination(e, 3, Direction.UP);
		ec.moveUp(e);
		Thread.sleep(5000);
		assertEquals(e.getCurrentFloor(),new Integer(3));

	}

	public void testMoveDown() throws InterruptedException {
		ec.addFloorToDestination(e, 1, Direction.DOWN);
		ec.moveDown(e);
		Thread.sleep(5000);
		assertEquals(e.getCurrentFloor(),new Integer(1));
	}

	public void testUpdateState() {
		ec.updateState(e, ElevatorState.DOWN);
		assertEquals(ElevatorState.DOWN, e.getState());
	}
}
