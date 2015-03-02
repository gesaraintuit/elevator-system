package com.intuit.elevator.control;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.intuit.elevator.SimpleElevator;

public class Controller implements Observer, Runnable {
	SimpleElevator[] elevators;
	int floors;
	int noOfElevators;
	ConcurrentLinkedQueue<Request> requests;
	
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
		    Request request = requests.poll();
			SimpleElevator e = getBestElevatorToServer();
			e.addFloorToDestination(request.getFloor());
		}
	}

	private SimpleElevator getBestElevatorToServer() {
		// TODO can be extended for multiple elevators
		return elevators[0];
	}

	public void update(Observable o, Object arg) {
		requests.add((Request)arg);
	}
}
