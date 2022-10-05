package com.car.pooling.model;

import java.util.ArrayList;

public class Car {
	private int noOfAvailableSeats, source, destination;
	private int carNo;
	private ArrayList<Integer> path;
	public Car(int carNo, int noOfAvailableSeats, int source, int destination, ArrayList<Integer> path) {
		this.destination = destination;
		this.noOfAvailableSeats = noOfAvailableSeats;
		this.noOfAvailableSeats = noOfAvailableSeats;
		this.path = (ArrayList<Integer>) path.clone();
		this.carNo = carNo;
	}
	
	public int getcarNo() {
		return this.carNo;
	}
	
	public int getNoOfAvailableSeats() {
		return noOfAvailableSeats;
	}
	
	public int getSource() {
		return source;
	}
	
	public int getDestination() {
		return destination;
	}
	
	public ArrayList<Integer> getPath() {
		return (ArrayList<Integer>) path.clone();
	}
	
	
	
}
