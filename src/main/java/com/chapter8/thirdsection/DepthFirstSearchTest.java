package com.chapter8.thirdsection;

import com.chapter8.firstsection.AdjGraph;

public class DepthFirstSearchTest {

	public static void main(String[] args) {
		AdjGraph g = new AdjGraph();
		g.createGraph();
		DepthFirstSearch search = new DepthFirstSearch(g);
		search.dfsTraverse(g);
	}

}
