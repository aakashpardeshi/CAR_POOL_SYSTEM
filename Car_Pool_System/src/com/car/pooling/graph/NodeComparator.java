package com.car.pooling.graph;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>  {
	@Override
	public int compare(Node n1, Node n2) {
		return (n1.getMinDistance() - n2.getMinDistance());
	}
	
}
