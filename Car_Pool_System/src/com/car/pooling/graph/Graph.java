package com.car.pooling.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Graph {
	int vertices;
	public ArrayList<ArrayList<Edge>> adjacencyList;
	public ArrayList<Node> Nodes;
	int[] parent;
	Integer[] test;
	int source;

	public Graph(int vertices) {
		this.vertices = vertices;
		adjacencyList = new ArrayList<>(vertices);
		Nodes = new ArrayList<Node>(vertices);
		parent = new int[vertices];
		for (int i = 0; i < vertices; i++) {
			adjacencyList.add(new ArrayList<Edge>());
			Nodes.add(new Node(i, Integer.MAX_VALUE));
		}
	}

	// Method for adding edge in the graph
	public void addEdge(int source, int destination, int weight) {
		Edge edge = new Edge(source, destination, weight);
		adjacencyList.get(source).add(edge);
	}

	// printing the minimum distance of all nodes in graph from source
	public void print(int source) {
		int[] test = djikstra(source);
		System.out.println("Node no Distance");
		for (int i = 0; i < test.length; i++) {
			System.out.print(i + "\t");
			if (test[i] == Integer.MAX_VALUE) {
				System.out.println("Not Reachable from " + source);
			} else {
				System.out.print(test[i] + "\n");
			}
		}
	}

	// Printing shortest path between source and destination
	public ArrayList<Integer> path(int source, int destination) {
		djikstra(source);
		ArrayList<Integer> path = new ArrayList<>();
		int count = destination;
		while (parent[count] != -1) {
			path.add(count);
			count = parent[count];
		}
		path.add(source);
		Collections.reverse(path);
		printPath(path);
		return path;
	}

	// Calculating shortest distance to all graph nodes from source node
	private int[] djikstra(int source) {
		Arrays.fill(parent, -1);
		this.source = source;
		boolean[] visited = new boolean[vertices];
		int[] minDist = new int[vertices];
		for (int i = 0; i < minDist.length; i++) {
			minDist[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<Node> pq = new PriorityQueue<Node>(15, new NodeComparator());
		minDist[source] = 0;
		pq.add(new Node(source, 0));
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			visited[n.getNodeNo()] = true;
			if (n.getMinDistance() > minDist[n.getNodeNo()]) {
				continue;
			}
			ArrayList<Edge> arr = adjacencyList.get(n.getNodeNo());
			for (int i = 0; i < arr.size(); i++) {
				if (visited[arr.get(i).getDestination()])
					continue;
				else {
					int updatedDist = n.getMinDistance() + arr.get(i).getWeight();
					if (updatedDist < minDist[arr.get(i).getDestination()]) {

						minDist[arr.get(i).getDestination()] = updatedDist;

						parent[arr.get(i).getDestination()] = n.getNodeNo();

						pq.add(new Node(arr.get(i).getDestination(), updatedDist));
					}
				}
			}
		}
		return minDist;
	}

	// Method to print the path from source to a particular destination
	private static void printPath(ArrayList<Integer> path) {
		for (int i = 0; i < path.size() - 1; i++) {
			System.out.print(path.get(i) + "-> ");
		}
		System.out.print(path.get(path.size() - 1) + "\n");

	}

}
