package com.intuit.building.elevator.application;

import java.util.Observable;

import com.intuit.building.elevator.SimpleElevator;
import com.intuit.building.elevator.control.RequestHandler;
import com.intuit.building.elevator.control.SimpleRequestHandler;
import com.intuit.building.elevator.floor.FloorControl;
import com.intuit.building.elevator.floor.SimpleFloorControl;
import com.intuit.building.elevator.Elevator;
import com.intuit.building.elevator.exception.InvalidElevatorRequestException;

public class ElevatorApplication {
	Elevator elevators;
	FloorControl floorControls[];
	int totalFloors;
	
	public void init(int floors){
		this.totalFloors = floors;
		this.elevators = new SimpleElevator(floors);
		FloorControl fc[] = new FloorControl[floors];
		RequestHandler re = new SimpleRequestHandler(floors, elevators);

		for(int i = 1 ; i <= floors ; i++){
			SimpleFloorControl sfc = new SimpleFloorControl(i);
			sfc.addObserver(re);
			fc[i-1] = sfc;
		}
		re.registerElevator(elevators);
		((Observable) elevators).addObserver(re);
		new Thread(re).start();
	}
	public static void main(String[] args) throws InvalidElevatorRequestException, InterruptedException {
		if(args.length !=1 ){
			printUsage();
			System.exit(0);
		}
		try {
			
		} catch (NumberFormatException e) {
			System.out.println("Input not a number : " + args[0]);
		}
		int floors = new Integer(args[0]);
		ElevatorApplication instance = new ElevatorApplication();
		instance.init(floors);
	}
	/**
	 * @return the elevators
	 */
	public Elevator getElevators() {
		return elevators;
	}
	/**
	 * @return the floorControls
	 */
	public FloorControl[] getFloorControls() {
		return floorControls;
	}
	/**
	 * @return the totalFloors
	 */
	public int getTotalFloors() {
		return totalFloors;
	}
	private static void printUsage() {
		System.out.println("Parameter required : no of Floors i.e. start.sh 5");
	
	}
}
