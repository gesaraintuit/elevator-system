/**
 * 
 */
package com.intuit.elevator;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;

import com.intuit.elevator.exception.InvalidElevatorRequestException;


/**
 * @author sunparmar
 *
 */
public class Elevator extends Thread {

	public static enum DoorState {
		OPEN, CLOSE, HOLD
	};

	int currentFloor;
	PriorityQueue<Integer> upQueue;
	PriorityQueue<Integer> downQueue;
	ElevatorState state;
	int lowestFloor;
	int highestFloor;
	public Elevator(int buildingSize,BlockingQueue<Integer> requestQueue,int speed){
		this.state = ElevatorState.STAND;
		upQueue = new PriorityQueue<Integer>(buildingSize);
		downQueue = new PriorityQueue<Integer>(buildingSize,Collections.reverseOrder());
		lowestFloor =0;
		highestFloor = buildingSize;
		currentFloor = 0;
		this.speed = 1000L*speed;
		doors = new Door();
	}
	class Door {
		DoorState state;
		public Door(){
			state = DoorState.CLOSE;
		}
		public void open() {
			state = DoorState.OPEN;
		}

		public void close() {
			state = DoorState.CLOSE;
		}

		public void hold(Integer seconds) throws InterruptedException {
			Thread.sleep(seconds*1000L);
		}

		@Override
		public String toString() {
			return "Door [state=" + state + "]";
		}
		
	}

	Door doors;
	private long speed;

	
	
	
	public void maintain() {
		state = ElevatorState.MAINTAINANCE;
	}

	public void completeMaintainance() {
		state = ElevatorState.STAND;
	}

	
	/**
	 * Keep moving up until queue is empty 
	 * @throws InvalidElevatorRequestException
	 * @throws InterruptedException 
	 */
	public void moveUp() throws InvalidElevatorRequestException, InterruptedException {
		logStatus();
		if (upQueue== null|| upQueue.isEmpty()) {
			standOrProcessOtherQueue();
		} else if ( upQueue.peek() == currentFloor) {
			upQueue.poll();
			openAndCloseDoors();
		}
		if(!upQueue.isEmpty()){
			Thread.sleep(speed);
			this.currentFloor++;
			moveUp();
		}else {
			standOrProcessOtherQueue();
		}
	}


	/**
	 * Keep moving down until queue is empty
	 * @throws InvalidElevatorRequestException
	 * @throws InterruptedException 
	 */
	public void moveDown() throws InvalidElevatorRequestException, InterruptedException {
		logStatus();
		if (downQueue== null|| downQueue.isEmpty() ) {
			standOrProcessOtherQueue();
		} else if ( downQueue.peek() == currentFloor) {
			downQueue.poll();
			openAndCloseDoors();
		}
		if(!downQueue.isEmpty()){
			Thread.sleep(speed);
			this.currentFloor--;
			moveDown();
		}else {
			standOrProcessOtherQueue();
		}
	}
	
	private void standOrProcessOtherQueue() throws InvalidElevatorRequestException, InterruptedException {
		if(state == ElevatorState.UP && downQueue!=null && !downQueue.isEmpty() ){
			state = ElevatorState.DOWN;
			moveDown();
		}else if(state == ElevatorState.DOWN && upQueue!=null && !upQueue.isEmpty() ){
			state = ElevatorState.UP;
			moveUp();
		}else{
			state = ElevatorState.STAND;	
		}
		logStatus();
	}
	private void openAndCloseDoors() throws InterruptedException {
		System.out.println("Open elevator door");
		this.doors.open();
		this.doors.hold(2);
		this.doors.close();
	}

	private void logStatus() {
		System.out.println(this);
	}
	/**
	 * Method controlled by Controller
	 * @param floor
	 */
	public void addFloorToDestination(int floor){
		if(floor > currentFloor)
			upQueue.add(floor);
		else
			downQueue.add(floor);
	}
	/**
	 * If Elevator is moving or not
	 * @return
	 */
	public boolean isStanding() {
		return state==ElevatorState.STAND;
	}
	/**
	 * Move elevator
	 */
	 public void run() {
	        try {
				this.move();
			} catch (InvalidElevatorRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	/**
	 * Starts elevator
	 * @throws InvalidElevatorRequestException 
	 * @throws InterruptedException 
	 */
	public void move() throws InvalidElevatorRequestException, InterruptedException {
		if(upQueue.isEmpty()){
			this.state = ElevatorState.DOWN;
			moveDown();
		} else{
			this.state = ElevatorState.UP;
			moveUp();
		}
	}	
	
	@Override
	public String toString() {
		return "Elevator [currentFloor=" + currentFloor + ", upQueue="
				+ upQueue + ", downQueue=" + downQueue + ", state=" + state
				+ ", doors" + doors + "]";
	}
}
