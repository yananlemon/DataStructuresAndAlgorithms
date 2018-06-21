package algorithms.chapter4.fourthsection;

import com.chapter8.dfs.Bag;

public class DirectedWeightedGraph {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	public static final double MAX_WEIGHT = 9999999999.0;
	
	/**顶点个数**/
	private int v;
	
	/**边数**/
	private int e;
	
	private Bag<Edge> adj[];
	
	public DirectedWeightedGraph(int v){
		this.v = v;
		this.adj = new Bag[v];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new Bag<Edge>();
		}
	}
	
	public void addEdge(int v, int w,double weight){
		validateVertex(v);
		validateVertex(w);
		Edge edge = new Edge(v, w, weight);
		this.adj[v].add(edge);
		this.e++;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
        s.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
        for (int i = 0; i < v; i++) {
            s.append(i + ": ");
            for (Edge e : adj[i]) {
                s.append(e);
            }
            s.append(NEWLINE);
        }
        return s.toString();
	}
	
	private void validateVertex(int v2) {
		if(v2 <0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	public double getWeight(int from,int to){
		validateVertex(from);
		validateVertex(to);
		for (Edge edge : this.adj[from]) {
			if(edge.to == to)
				return edge.weight;
		}
		return MAX_WEIGHT;
	}
	
	public int getV(){
		return this.v;
	}
	
	public static void main(String[] args) {
		DirectedWeightedGraph graph = new DirectedWeightedGraph(6);
		graph.addEdge(0, 2, 10);
		graph.addEdge(0, 4, 30);
		graph.addEdge(0, 5, 100);
		graph.addEdge(1, 2, 5);
		graph.addEdge(2, 3, 50);
		graph.addEdge(3, 5, 10);
		graph.addEdge(4, 3, 20);
		graph.addEdge(4, 5, 60);
		System.out.println(graph);
		
	}

}
class Edge {
	int from;
	int to;
	double weight;
	public Edge(int from, int to, double weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}
	
}
