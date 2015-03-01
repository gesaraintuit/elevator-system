package com.intuit.elevator.simulator;

import java.util.concurrent.BlockingQueue;

import com.intuit.elevator.Elevator;
import com.intuit.elevator.exception.InvalidElevatorRequestException;

public class ElevatorSimulator {

	public static void main(String[] args) throws InvalidElevatorRequestException, InterruptedException {
		//Initialize elevators
		//Initialize floorControls
		//Initialize DecisionEngine
		
		Elevator e = new Elevator(5,null,1);
		e.addFloorToDestination(2);
		e.addFloorToDestination(4);
		e.start();
		Thread.sleep(3000);
		e.addFloorToDestination(1);
		e.addFloorToDestination(2);

	}

}
