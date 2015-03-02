/**
 * 
 */
package com.intuit.elevator.control;

import java.util.PriorityQueue;

import org.apache.commons.collections4.CollectionUtils;

import com.intuit.elevator.Elevator;
import com.intuit.elevator.ElevatorState;

/**
 * @author sunparmar
 *
 */
public class SimpleElevatorController implements ElevatorController {

	/* (non-Javadoc)
	 * @see com.intuit.elevator.control.ElevatorController#move(com.intuit.elevator.Elevator)
	 */
	public void move(Elevator e) {
		if (e.isStanding()){
		if(CollectionUtils.isEmpty(e.getUpQueue())){
			e.setState(ElevatorState.DOWN);
			moveDown(e);
		} else if(CollectionUtils.isEmpty(e.getDownQueue())){
			e.setState(ElevatorState.UP);
			moveUp(e);
		} else{
			e.setState(ElevatorState.IDLE);
		}
		}
	}

	/* (non-Javadoc)
	 * @see com.intuit.elevator.control.ElevatorController#moveUp(com.intuit.elevator.Elevator)
	 */
	public void moveUp(Elevator e) {
		 PriorityQueue<Integer> uq = e.getUpQueue();
		if (CollectionUtils.isEmpty(uq) ) {
			standOrProcessOtherQueue(e);
		} else if ( uq.peek().equals(e.getCurrentFloor())) {
			uq.poll();
			serverTheFloor(e);
		}
		if(!uq.isEmpty()){
			moveOneUp(e);
			moveUp(e);
		}else {
			standOrProcessOtherQueue(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.intuit.elevator.control.ElevatorController#moveDown(com.intuit.elevator.Elevator)
	 */
	public void moveDown(Elevator e) {
		 PriorityQueue<Integer> dq = e.getDownQueue();
		if (CollectionUtils.isEmpty(dq)) {
			standOrProcessOtherQueue(e);
		} else if ( dq.peek().equals(e.getCurrentFloor())) {
			dq.poll();
			serverTheFloor(e);
		}
		if(!dq.isEmpty()){
			moveOneDown(e);
			moveDown(e);
		}else {
			standOrProcessOtherQueue(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void standOrProcessOtherQueue(Elevator e) {
		PriorityQueue dq = e.getDownQueue();
		PriorityQueue uq = e.getUpQueue();
		if(e.getState() == ElevatorState.UP && !CollectionUtils.isEmpty(dq) ){
			e.setState(ElevatorState.DOWN);
			moveDown(e);
		}else if(e.getState() == ElevatorState.DOWN && !CollectionUtils.isEmpty(uq)  ){
			e.setState(ElevatorState.UP);
			moveUp(e);
		}else{
			e.setState(ElevatorState.IDLE);	
		}
	}
	/**
	 * Add floor to the destination queue
	 */
	public void addFloorToDestination(Elevator e, int floor) {
		if(floor > e.getCurrentFloor())
			e.getUpQueue().add(floor);
		else
			e.getDownQueue().add(floor);
	}
	
	private void serverTheFloor(Elevator e) {
		//publish door activity
		e.openDoor();
		e.holdDoor();
		e.closeDoor();
	}

	public void updateState(Elevator e, ElevatorState state) {
		//TODO : publish changed state
		e.setState(state);
	}

	public void moveOneUp(Elevator e) {
		//TODO : publish changed state
		//move slowly add sleep
		e.moveUp();
	}
	public void moveOneDown(Elevator e) {
		//TODO : publish changed state
		//move slowly add sleep
		e.moveDown();
	}
}
