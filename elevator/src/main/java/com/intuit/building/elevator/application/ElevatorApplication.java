package com.intuit.building.elevator.application;

import java.util.Observable;
import java.util.Scanner;

import com.intuit.building.elevator.Elevator;
import com.intuit.building.elevator.SimpleElevator;
import com.intuit.building.elevator.control.RequestHandler;
import com.intuit.building.elevator.control.SimpleRequestHandler;
import com.intuit.building.elevator.exception.InvalidElevatorRequestException;
import com.intuit.building.elevator.floor.Direction;
import com.intuit.building.elevator.floor.FloorControl;
import com.intuit.building.elevator.floor.SimpleFloorControl;

public class ElevatorApplication {
	Elevator elevators;
	FloorControl floorControls[];
	int totalFloors;

	public void init(int floors) {
		this.totalFloors = floors;
		this.elevators = new SimpleElevator(floors);
		this.floorControls = new FloorControl[floors];
		RequestHandler re = new SimpleRequestHandler(floors, elevators, true);

		for (int i = 1; i <= floors; i++) {
			SimpleFloorControl sfc = new SimpleFloorControl(i);
			sfc.addObserver(re);
			this.floorControls[i - 1] = sfc;
		}
		re.registerElevator(elevators);
		((Observable) elevators).addObserver(re);
		new Thread(re).start();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args)
			throws InvalidElevatorRequestException, InterruptedException {
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
		System.out.println("Enter commands to elevators and floor controls");
		System.out
				.println("First line no of lines of commands where each command is separated by a comma ,");
		System.out.println("There are three types of commands");
		System.out
				.println("Floor control f:<floor no>:<direction> e.g. f:2:d is calling elevator on floor 2 to go down");
		System.out
				.println("Elevator control e:<floor no> e.g. e:4 is pressing button inside elevator to go floor 4");
		System.out
				.println("Delay in miliseconds d:<delay> e.g. d:5000 wait for 5 seconds before moving to next command");
		System.out
				.println("You can copy paste on command line to quickl or reuse");
		int count = 0;

		try {
			count = in.nextInt();
		} catch (NumberFormatException ex) {
			System.out
					.println("Incorrect input, the program will exit now. Please try again later.");
		}
		while (count-- > 0) {
			String line = in.nextLine();
			String[] commands = line.split(",");
			for (String cmd : commands) {
				executeCommand(cmd, floors, fc, e);
			}
		}
	}

	/**
	 * fc[5].buttonPressed(Direction.UP);//req from 6th floor to go up
	 * fc[7].buttonPressed(Direction.DOWN);//req from 8th floor to go down
	 * fc[3].buttonPressed(Direction.DOWN);//req from 4rd floor to go down
	 * fc[2].buttonPressed(Direction.UP);//req from 3rd floor to go up
	 * fc[9].buttonPressed(Direction.UP); e.buttonPressed(7);
	 * e.buttonPressed(9); Thread.sleep(3000); e.buttonPressed(1);
	 * 
	 * @param e
	 * @param fc
	 **/

	private static void executeCommand(String cmd, int maxFloors,
			FloorControl[] fc, Elevator e) {
		try {
			if (cmd != null && !cmd.trim().equals("")) {
				String[] cbits = cmd.split(":");

				if (cbits[0].equals("f")) {
					int fl = new Integer(cbits[1]).intValue();
					Direction d = null;
					if (cbits[2].equals("d"))
						d = Direction.DOWN;
					else if (cbits[2].equals("u"))
						d = Direction.UP;
					fc[fl - 1].buttonPressed(d);
				} else if (cbits[0].equals("e")) {
					int efl = new Integer(cbits[1]).intValue();
					e.buttonPressed(efl);
				} else if (cbits[0].equals("d")) {
					long delay = new Long(cbits[1]).longValue();
					Thread.sleep(delay);
				} else {
					System.out.println("Invalid command " + cmd
							+ ". ignoring this");
				}

			}
		} catch (Exception e2) {
			System.out.println("Invalid command " + cmd + ". ignoring this");
		}
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
}
