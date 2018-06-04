package algorithms.chapter4.firstsection;

public class TheNinthQuestion {

	public static void main(String[] args) {
		Graph g = new Graph(12);
		g.addEdge(8,4 );
		g.addEdge(2,3 );
		g.addEdge(1, 11);
		g.addEdge(0,6 );
		g.addEdge(3,6 );
		g.addEdge(10,3 );
		g.addEdge(7,11 );
		g.addEdge(7,8 );
		g.addEdge(11,8 );
		g.addEdge(2,0 );
		g.addEdge(6,2 );
		g.addEdge(5,2 );
		g.addEdge(5,10 );
		g.addEdge(5,0 );
		g.addEdge(8,1 );
		g.addEdge(4, 1);
		DepthFirstSearchPaths dfs = new DepthFirstSearchPaths(g, 0);
	}

}
