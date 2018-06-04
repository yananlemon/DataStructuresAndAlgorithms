package algorithms.chapter4.firstsection;

public class TheTenThQuestion {

	public static void main(String[] args) {
		Graph g = new Graph(8);
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(4,5);
		g.addEdge(5,6);
		g.addEdge(6,7);
		DepthFirstSearch dfs = new DepthFirstSearch(g);
		System.out.println("G是连通图吗？"+dfs.checkWhetherConnected(g));
		for(int i = 0; i < g.getV(); i++) {
			g.deleteVertex(i);
			System.out.println("G是连通图吗？"+dfs.checkWhetherConnected(g));
		}
	}

}
