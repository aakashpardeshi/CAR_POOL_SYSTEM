package com.car.pooling.graph;

public class Edge {
	private Integer source, destination, weight;

	public Edge(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public int getSource() {
		return this.source;
	}
	public int getDestination() {
		return this.destination;
	}
	public Integer getWeight() {
		return this.weight;
	}
	
	
	
}
