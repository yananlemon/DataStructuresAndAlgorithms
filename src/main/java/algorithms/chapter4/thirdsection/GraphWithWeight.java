package algorithms.chapter4.thirdsection;

import com.chapter3.text.DoublyLinkedList;

public class GraphWithWeight {


	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**顶点数量**/
	private int v;
	
	/**边数量**/
	private int e;
	
	/**邻接表**/
	private DoublyLinkedList<Edge>[] adj;
	
	/**
	 * 创建一幅加权无向图
	 * @param v
	 */
	public GraphWithWeight(int v){
		this.v = v;
		adj = new DoublyLinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new DoublyLinkedList<Edge>(); 
		}
	}
	
	public void addEdge(int v,int w,double weight){
		validateVertex(v);
		validateVertex(w);
		Edge edge = new Edge(v, w, weight);
		this.adj[v].addToHead(edge);
		edge = new Edge(w, v, weight);
		this.adj[w].addToHead(edge);
		e++;
	}
	
	public boolean hasEdge(int v, int w) {
		validateVertex(v);
    	validateVertex(w);
    	for(int i = 0; i < adj[v].size(); i++) {
    		if(adj[v].get(i).getW() == w)
    			return true;
    	}
    	return false;
	}
	
	public void validateVertex(int v2) {
		if(v2 <0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	public int getV(){
		return v;
	}
	
	public DoublyLinkedList<Edge> adj(int v){
		return adj[v];
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
        for (int i = 0; i < v; i++) {
            sb.append(i + ": ");
            for (int w = 0; w < adj[i].size();w++) {
            	Edge edge = adj[i].get(w);
                sb.append(edge.getV() + "--"+edge.getW()+" : "+edge.getWeight()+"  ");
            }
            sb.append(NEWLINE);
        }
		return sb.toString();
	}

	public static void main(String[] args) {
		GraphWithWeight g = new GraphWithWeight(7);
		g.addEdge(0,1,12 );
		g.addEdge(1,2,10 );
		g.addEdge(2,3,3 );
		g.addEdge(3,4,3 );
		g.addEdge(4,6,8 );
		g.addEdge(6,0,14 );
		g.addEdge(0,5,16 );
		g.addEdge(1,5,7 );
		g.addEdge(5,2,6 );
		g.addEdge(2,4,5 );
		g.addEdge(5,4,2 );
		g.addEdge(6,5,9 );
		System.out.println(g);
	}

	public double getWeight(Integer v, Integer w) {
		validateVertex(v);
    	validateVertex(w);
    	for(int i = 0; i < adj[v].size(); i++) {
    		if(adj[v].get(i).getW() == w)
    			return adj[v].get(i).getWeight();
    	}
		return -1;
	}
}
