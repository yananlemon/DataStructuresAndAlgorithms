package com.chapter8.thirdsection;

import com.chapter8.firstsection.AdjGraph;

public class DepthFirstSearchTest {

	public static void main(String[] args) {
		AdjGraph g = new AdjGraph(8,9);//8个顶点9条边
		g.addVertex("v1", 0);
		g.addVertex("v2", 1);
		g.addVertex("v3", 2);
		g.addVertex("v4", 3);
		g.addVertex("v5", 4);
		g.addVertex("v6", 5);
		g.addVertex("v7", 6);
		g.addVertex("v8", 7);
		g.addEdge(0,2);
		g.addEdge(0,1);
		g.addEdge(1,4);
		g.addEdge(1,3);
		g.addEdge(2,6);
		g.addEdge(2,5);
		g.addEdge(3,7);
		g.addEdge(3,4);
		g.addEdge(5,6);
		g.showGraph();
		DepthFirstSearch search = new DepthFirstSearch(g);
		//search.dfsTraverse(g);
		search.dfsTraverseWithoutRecursion(g);
	}

}
