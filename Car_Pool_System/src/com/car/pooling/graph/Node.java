package com.car.pooling.graph;

import java.util.ArrayList;

public class Node {
	private int nodeNo, minDistance;
	public ArrayList<Integer> cars = new ArrayList<>();
	public Node(int nodeNo, int minDistance) {
		this.minDistance = minDistance;
		this.nodeNo = nodeNo;
	}

	public void addCar(int carNo) {
		cars.add(carNo);
	}
	
	public void removeCar(int carNo) {
		if(cars.contains(carNo)) {
			cars.remove(carNo);
		}
	}
	
	
	public int getNodeNo() {
		return this.nodeNo;
	}
	
	public int getMinDistance() {
		return this.minDistance;
	}
}
