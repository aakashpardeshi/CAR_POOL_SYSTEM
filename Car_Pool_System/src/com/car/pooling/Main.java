package com.car.pooling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.car.pooling.graph.Edge;
import com.car.pooling.graph.Graph;
import com.car.pooling.model.Car;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			Graph graph = new Graph(9);
			graph.addEdge(0, 1, 4);
			graph.addEdge(0, 7, 8);
			graph.addEdge(7, 1, 11);
			graph.addEdge(1, 2, 8);
			graph.addEdge(2, 5, 4);
			graph.addEdge(7, 8, 7);
			graph.addEdge(7, 6, 1);
			graph.addEdge(2, 8, 2);
			graph.addEdge(2, 3, 7);
			graph.addEdge(8, 6, 6);
			graph.addEdge(5, 3, 14);
			graph.addEdge(5, 4, 10);
			graph.addEdge(3, 4, 9);
			graph.addEdge(6, 5, 2);
			System.out.println("Connected nodes in the city shown using Adjacency List : ");
			printAdjacencyList(graph.adjacencyList);
			System.out.println("Path for Car 1");
			ArrayList<Integer> path1 = graph.path(0, 8);
			System.out.println("Path for Car 2");
			ArrayList<Integer> path2 = graph.path(2, 6);
			System.out.println("Path for Car 3");
			ArrayList<Integer> path3 = graph.path(3,4);
			Car c1 = new Car(1, 3, 0, 8, path1);
			Car c2 = new Car(2, 3, 2, 6, path2);
			Car c3 = new Car(3, 3, 3, 4, path3);
			addCarPathtoNodes(graph, c1);
			addCarPathtoNodes(graph, c2);
			addCarPathtoNodes(graph, c3);
			System.out.println("----------Welcome to our Car Pooling service----------");
			int choice = 0;
			do {
				System.out.println("Do you want to take a ride ? (1-yes/0-No)");
				choice = sc.nextInt();
				if(choice == 1) {
					System.out.println("Please enter your source : ");
					int source = sc.nextInt();
					System.out.println("Please enter your destination : ");
					int destination = sc.nextInt();
					getCars(source, destination, graph);
					System.out.println("--------------------------------------------------");
				}
			} while (choice != 0);	
			sc.close();
	}
	
	private static void addCarPathtoNodes(Graph g, Car c) {
		ArrayList<Integer> path = c.getPath();
		for(Integer p : path) {
			g.Nodes.get(p).addCar(c.getcarNo());;
		}
	}
	
	private static ArrayList<Integer> getCars(int source, int destination, Graph g){
		Set<Integer> s1 = new HashSet<>();
		Set<Integer> s2 = new HashSet<>();
	
		s1.addAll(g.Nodes.get(source).cars);
		s2.addAll(g.Nodes.get(destination).cars);
		s1.retainAll(s2);
		ArrayList<Integer> arr = new ArrayList<>(s1);
		if(!arr.isEmpty()) {
			System.out.println("The available cars from "+source+" to "+destination+" are : ");
			for(Integer i: arr) {
				System.out.print("Car"+i+" ");
			}
			System.out.println();
		}

		
		if(arr.size() == 0) {
			s1.clear();
			System.out.println("Sorry No Cars Available to "+source+" from "+destination);
			System.out.println("Showing nearest Alternative paths : ");
			
			ArrayList<Edge> e = g.adjacencyList.get(source);
			Collections.sort(e, (e1, e2)-> e1.getWeight().compareTo(e2.getWeight()));
			
			for(Edge edge : e) {
				s1.clear();
				s1.addAll(g.Nodes.get(edge.getDestination()).cars);
				s1.retainAll(s2);
				if(!s1.isEmpty()) {
					arr.clear();
					arr.addAll(s1);
					System.out.println("From Node"+edge.getDestination());
					for(Integer i: arr) {
						System.out.print("Car"+i+" ");
					}
					System.out.println();
					break;
				}
				
			}
			if(arr.isEmpty()) {
				System.out.println("Sorry no Alternate path exists ");
			}
		}
		
		return arr;
	}
	
	

	private static void printAdjacencyList(ArrayList<ArrayList<Edge>> adjacencyList) {
		System.out.println();
		for (int i = 0; i < adjacencyList.size(); i++) {
			ArrayList<Edge> arr = adjacencyList.get(i);
			System.out.print(i + " : ");
			if (arr.size() > 0) {
				for (int j = 0; j < arr.size() - 1; j++) {
					System.out.print(arr.get(j).getDestination() + "->");

				}
				System.out.print(arr.get(arr.size() - 1).getDestination());
				System.out.println();
			}else {
				System.out.println();
			}
		}
		System.out.println();
	}
}
