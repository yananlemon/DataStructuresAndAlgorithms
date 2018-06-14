package algorithms.chapter4.firstsection;

import java.util.Stack;

public class DepthFirstSearchPaths {
	
	private boolean marked[];
	
	private int start;//起点
	
	private int[] edgeTo;
	
	
	public DepthFirstSearchPaths(Graph g,int start) {
		marked = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		this.start = start;
		dfs(g, start);
		System.out.println(edgeTo);
	}
	
	private void dfs(Graph g,int v){
		marked[v] = true;
		for (int w: g.adj(v)) {
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(g,w);
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != start; x = edgeTo[x]){
			path.push(x);
		}
		path.push(start);
		return path;
	}

	public static void main(String[] args) {
		/*Graph g = new Graph(6);
		g.addEdge(0,5);
		g.addEdge(2,4);
		g.addEdge(2,3);
		g.addEdge(1,2);
		g.addEdge(0,1);
		g.addEdge(3,4);
		g.addEdge(3,5);
		g.addEdge(0,2);*/
		
		Graph g = new Graph(8);
		g.addEdge(0,1);
		g.addEdge(0,2);
		g.addEdge(1,3);
		g.addEdge(3,2);
		g.addEdge(3,4);
		g.addEdge(3,5);
		g.addEdge(4,5);
		g.addEdge(4,6);
		g.addEdge(5,6);
		g.addEdge(5,7);
		g.addEdge(6,7);
		
		DepthFirstSearchPaths s = new DepthFirstSearchPaths(g, 3);
		for (int v = 0; v < g.getV(); v++) {
			System.out.print("0 到 "+v+":");
			if(s.hasPathTo(v)){
				for (int x : s.pathTo(v)) {
					if(x == 0)
						System.out.print(x);
					else
						System.out.print("-"+x);
				}
			}
			System.out.println();
		}
	}

}
