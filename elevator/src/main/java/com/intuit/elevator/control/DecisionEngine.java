package com.intuit.elevator.control;

import com.intuit.elevator.Elevator;
import com.intuit.elevator.FloorControl;

public class DecisionEngine {
	Elevator[] elevators;
	FloorControl[] floorControls;
	
	public void handleFloorControlEvent(FloorControl floorControl, DirectionEnum direction){
		Elevator elevator = getElevatorToServe(floorControl.floorId,direction);		
	}
	
	private Elevator getElevatorToServe(Integer floorId, DirectionEnum direction) {
		return elevators[0];
	}

	public void hadleElevator(Elevator elevator, int floor){
		
	}
}
