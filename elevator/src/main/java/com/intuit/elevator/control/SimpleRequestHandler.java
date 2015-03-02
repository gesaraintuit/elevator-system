package com.intuit.elevator.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.intuit.elevator.Elevator;

public class SimpleRequestHandler implements RequestHandler {
	List<Elevator> elevators;
	int floors;
	ConcurrentLinkedQueue<Request> requests;
	ElevatorController eController = new SimpleElevatorController();
	
	public SimpleRequestHandler(int floors, Elevator e) {
		super();
		this.elevators = new ArrayList<Elevator>();
		elevators.add(e);
		this.floors = floors;
		this.requests = new ConcurrentLinkedQueue<Request>();
	}
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			if(!requests.isEmpty()){
				Request request = requests.poll();
				Elevator e = getBestElevatorToServe(request);
				if(request.getFloor() != -1){
					eController.addFloorToDestination(e, request.getFloor());
					eController.move(e);
				} else{
					eController.updateState(e,request.getState());
				}
			}
		}
	}

	private Elevator getBestElevatorToServe(Request request) {
		// TODO can be extended for multiple elevators
		return elevators.get(0);
	}
	
	public void update(Observable o, Object arg) {
		requests.add((Request)arg);
	}

	public void registerElevator(Elevator e) {
		elevators.add(e);
	}
}
