package com.intuit.building.elevator.application;

import java.util.Observable;
import java.util.Scanner;

import com.intuit.building.elevator.SimpleElevator;
import com.intuit.building.elevator.control.RequestHandler;
import com.intuit.building.elevator.control.SimpleRequestHandler;
import com.intuit.building.elevator.floor.Direction;
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
		this.floorControls = new FloorControl[floors];
		RequestHandler re = new SimpleRequestHandler(floors, elevators,true);

		for(int i = 1 ; i <= floors ; i++){
			SimpleFloorControl sfc = new SimpleFloorControl(i);
			sfc.addObserver(re);
			this.floorControls[i-1] = sfc;
		}
		re.registerElevator(elevators);
		((Observable) elevators).addObserver(re);
		new Thread(re).start();
	}
	public static void main(String[] args) throws InvalidElevatorRequestException, InterruptedException {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter no of Floors :");
		int floors = 0;
		try {
			floors = in.nextInt();
		} catch (NumberFormatException e) {
			System.out.println("Input not a number : " + args[0]);
		}
		
		ElevatorApplication instance = new ElevatorApplication();
		instance.init(floors);
		Elevator e = instance.getElevators();
		FloorControl[] fc = instance.getFloorControls();
		fc[5].buttonPressed(Direction.UP);//req from 6th floor to go up
		fc[7].buttonPressed(Direction.DOWN);//req from 8th floor to go down
		fc[3].buttonPressed(Direction.DOWN);//req from 4rd floor to go down
		fc[2].buttonPressed(Direction.UP);//req from 3rd floor to go up
		fc[9].buttonPressed(Direction.UP);
		e.buttonPressed(7);
		e.buttonPressed(9);
		Thread.sleep(3000);
		e.buttonPressed(1);
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
