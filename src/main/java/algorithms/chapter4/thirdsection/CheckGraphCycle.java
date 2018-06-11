package algorithms.chapter4.thirdsection;

import algorithms.chapter4.firstsection.Graph;

public class CheckGraphCycle {

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0,1);
		g.addEdge(0,3);
		g.addEdge(3,5);
		g.addEdge(5,2);
		g.addEdge(5,4);
		g.addEdge(4,1);
		System.out.println(g);
		CheckGraphCycle check = new CheckGraphCycle();
		System.out.println("是否有环："+check.hasCycle(g));
	}
	
	public boolean hasCycle(Graph g) {
		UnionSets us = new UnionSets(g.getV());
		if(g == null || g.getV() == 0)
			return false;
		for (int i = 0; i < g.getEdges().size(); i++) {
			int v = g.getEdges().get(i)[0];
			int w = g.getEdges().get(i)[1];
			System.out.println(v+","+ w);
			v = us.find(v);
			w = us.find(w);
			if(v == w)
				return true;
			us.union(v, w);
		}
		return false;
	}

}
