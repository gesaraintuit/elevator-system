package com.intuit.elevator.control;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.intuit.elevator.Elevator;
import com.intuit.elevator.exception.InvalidElevatorRequestException;

public class Controller implements Observer, Runnable {
	Elevator[] elevators;
	int floors;
	int noOfElevators;
	ConcurrentLinkedQueue<Request> requests;
	
	
	public void handleFloorControlEvent(Integer floor, Direction direction){
		Elevator elevator = getElevatorToServe(floor,direction);	
		elevator.addFloorToDestination(floor);
	}
	
	private Elevator getElevatorToServe(Integer floorId, Direction direction) {
		return elevators[0];
	}

	public void hadleElevatorEvent(Elevator elevator, int floor) throws InvalidElevatorRequestException{
		elevator.addFloorToDestination(floor);
		if(elevator.isStanding()){
			elevator.start();
		} 
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
		    Request request = requests.poll();
			Elevator e = getBestElevatorToServer();
			e.addFloorToDestination(request.getFloor());
		}
	}

	private Elevator getBestElevatorToServer() {
		// TODO can be extended for multiple elevators
		return elevators[0];
	}

	public void update(Observable o, Object arg) {
		requests.add((Request)arg);
	}
}
