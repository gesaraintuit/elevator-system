package com.intuit.elevator.simulator;

import com.intuit.building.elevator.floor.Direction;
import com.intuit.building.elevator.floor.FloorControl;
import com.intuit.building.elevator.floor.SimpleFloorControl;
import com.intuit.elevator.SimpleElevator;
import com.intuit.elevator.control.RequestHandler;
import com.intuit.elevator.control.SimpleRequestHandler;
import com.intuit.elevator.exception.InvalidElevatorRequestException;

public class ElevatorSimulator {

	public static void main(String[] args) throws InvalidElevatorRequestException, InterruptedException {
		//Initialize elevators
		//Initialize floorControls
		//Initialize DecisionEngine
		int floors = 5;
		int speed = 2;

		SimpleElevator e = new SimpleElevator(floors,speed);
		RequestHandler re = new SimpleRequestHandler();
		re.registerElevator(e);
		e.addObserver(re);
		FloorControl fc[] = new FloorControl[floors];
		for(int i = 1 ; i <= floors ; i++){
			SimpleFloorControl sfc = new SimpleFloorControl(i);
			sfc.addObserver(re);
			fc[i-1] = sfc;
		}
		e.buttonPressed(1);
		e.buttonPressed(2);
		fc[2].buttonPressed(Direction.DOWN);
	}
}
