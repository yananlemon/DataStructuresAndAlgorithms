package algorithms.chapter4.secondsection;

import com.chapter4.text.StackL;

public class DirectedCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private StackL<Integer> cycle;
	private boolean[] onStack;
	
	public DirectedCycle(Digraph g){
		onStack = new boolean[g.V()];
		edgeTo = new int[g.V()];
		marked =  new boolean[g.V()];
		for( int v = 0; v < g.V(); v++ )
			if( !marked[v] ) dfs(g,v);
	}
	
	private void dfs(Digraph g,int v){
		onStack[v] = true;
		marked[v] = true;
		for( int w : g.adj(v)){
			if( this.hasCycle() )
				return;
			else if( !marked[w] ){
				edgeTo[w] = v;
				dfs(g, w);
			}else if( onStack[w] ){
				cycle = new StackL<Integer>();
				for( int x = v; x != w; x= edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	
	public boolean hasCycle(){
		return cycle != null;
	}
	
	public StackL<Integer> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		Digraph g = new Digraph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		
		/*Digraph g = new Digraph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 5);
		
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);*/
		
		
		/*Digraph g = new Digraph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 1);*/
		
		DirectedCycle cycle = new DirectedCycle(g);
		if( cycle.hasCycle() ){
			System.out.println("has cycle");
			StackL<Integer> cycleStack = cycle.cycle();
			while( !cycleStack.isEmpty() )
				System.out.print(cycleStack.pop()+"->");
		}else{
			System.out.println("no cycle");
		}
	}
}
