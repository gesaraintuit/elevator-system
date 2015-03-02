package com.intuit.elevator.simulator;

import java.util.concurrent.BlockingQueue;

import com.intuit.building.elevator.floor.FloorControl;
import com.intuit.elevator.Elevator;
import com.intuit.elevator.SimpleElevator;
import com.intuit.elevator.exception.InvalidElevatorRequestException;

public class ElevatorSimulator {

	public static void main(String[] args) throws InvalidElevatorRequestException, InterruptedException {
		//Initialize elevators
		//Initialize floorControls
		//Initialize DecisionEngine
		

		Elevator e = new SimpleElevator(5,1);
		e.addFloorToDestination(2);
		e.addFloorToDestination(4);
		new Thread(e).start();
		Thread.sleep(3000);
		e.addFloorToDestination(1);
		e.addFloorToDestination(2);

	}

}
