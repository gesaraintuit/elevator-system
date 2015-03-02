package com.intuit.elevator;

import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleElevator e = new SimpleElevator(10,null,10);
		e.addFloorToDestination(2);
		e.addFloorToDestination(4);
		e.addFloorToDestination(8);
		e.run();
		e.addFloorToDestination(9);
		e.addFloorToDestination(7);
		e.addFloorToDestination(1);
	}

}
