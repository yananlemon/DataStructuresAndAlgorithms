package algorithms.chapter4.firstsection;

public class DepthFirstSearch {
	
	private boolean marked[];
	
	private int count;
	
	
	public DepthFirstSearch(Graph g) {
		super();
		marked = new boolean[g.getV()];
	}
	
	public void dfs(Graph g,int v){
		marked[v] = true;
		count++;
		for (int w: g.adj(v)) {
			if(!marked[w]){
				dfs(g,w);
			}
		}
	}
	
	public boolean checkWhetherConnected(Graph g){
		dfs(g, 0);
		if(count == g.getV())
			return true;
		return false;
	}

	public static void main(String[] args) {
		
	}

}
