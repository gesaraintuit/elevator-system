package com.intuit.elevator;

import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ia = {11,2,4,9,23,7,3,5};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i : ia) {
			pq.add(i);
		}
		System.out.println(pq);
		System.out.println(pq.poll());
		System.out.println(pq);
		System.out.println(pq.contains(5));
		pq.remove(9);
		System.out.println(pq);
		System.out.println(pq.poll());
		System.out.println(pq);
	}

}
