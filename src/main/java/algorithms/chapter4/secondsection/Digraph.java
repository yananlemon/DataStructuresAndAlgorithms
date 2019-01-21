package algorithms.chapter4.secondsection;

import com.chapter3.text.SingleLinkedList;

public class Digraph {

	/**顶点个数**/
	private final int V;
	
	/**边**/
	private int E;
	
	private SingleLinkedList[] adj;
	
	public Digraph(int v){
		this.V = v;
		this.E = 0;
		adj = new SingleLinkedList[V];
		for(int i = 0; i < v; i++){
			adj[i] = new SingleLinkedList();
		}
	}
	
	public Iterable<Integer> adj(int w){
		return adj[w];
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v,int w){
		this.adj[v].addToTail(w);
		this.E++;
	}
}
