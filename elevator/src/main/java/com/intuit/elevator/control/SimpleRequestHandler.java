package com.intuit.elevator.control;

import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.intuit.elevator.Elevator;
import com.intuit.elevator.SimpleElevator;

public class SimpleRequestHandler implements RequestHandler {
	Elevator[] elevators;
	int floors;
	int noOfElevators;
	ConcurrentLinkedQueue<Request> requests;
	
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
		    Request request = requests.poll();
			Elevator e = getBestElevatorToServer();
			if(request.getFloor() != -1){
				e.addFloorToDestination(request.getFloor());
			} else{
				e.updateState(request.getState());
			}
		}
	}

	private Elevator getBestElevatorToServer() {
		// TODO can be extended for multiple elevators
		return elevators[0];
	}

	public void update(Observable o, Object arg) {
		requests.add((Request)arg);
	}

	public void registerElevator(Elevator e) {
		elevators[0] = e;
		
	}
}
