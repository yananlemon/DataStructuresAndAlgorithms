package algorithms.chapter4.firstsection;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;

public class TheTenthQuestion {

	public static void main(String[] args) {
		/*GraphN g = new GraphN(6);
		g.addEdge(0,5);
		g.addEdge(0,2);
		g.addEdge(0,1);
		g.addEdge(1,3);
		g.addEdge(2,3);
		g.addEdge(1,2);
		g.addEdge(2,5);
		g.addEdge(3,4);
		g.addEdge(4,5);*/
		GraphN g = new GraphN(8);
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(4,5);
		g.addEdge(5,6);
		g.addEdge(6,7);
		System.out.println(g);
		DepthFirstSearchForGraphN dfs = new DepthFirstSearchForGraphN(g);
		System.out.println("G是连通图吗？"+dfs.checkWhetherConnected(g));
		/*for(int i = 0; i < g.getVertexes().length; i++) {
			g.deleteVertex(i);
			System.out.println(g);
			dfs = new DepthFirstSearchForGraphN(g);
			System.out.println("G是连通图吗？"+dfs.checkWhetherConnected(g));
		}*/
		g.deleteVertex(3);
		System.out.println(g);
		dfs = new DepthFirstSearchForGraphN(g);
		System.out.println("G是连通图吗？"+dfs.checkWhetherConnected(g));
	}

}

class GraphN{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**图中顶点的数量**/
	private int v;

	/**图中边的数量**/
	private int e;

	/**顶点数组**/
	private int[] vertexes;
	
	/**邻接表**/
	private SingleLinkedList[] adj;
	
	/**
	 * 根据{@code v}初始化一个空的图对象。
	 * @param v 图中顶点的数量
	 * @throws IllegalArgumentException 如果 {@code V < 0}
	 */
	public GraphN(int v){
		if(v < 0)
			throw new IllegalArgumentException("顶点的数量不能是负数！");
		this.v = v;
		this.adj = new SingleLinkedList[v];
		this.vertexes = new int[v];
		for (int i = 0; i < v; i++) {
			vertexes[i] = i;
			adj[i] = new SingleLinkedList();
		}
	}
	
	
	
	public int[] getVertexes() {
		return vertexes;
	}


	/**
	 * 添加两条边到无向图。
	 *
	 * @param  v 图中的一个顶点
	 * @param  w 图中的另一个顶点
	 * @throws IllegalArgumentException 除非不满足这两个条件 {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		e++;
		adj[v].addToHead(w);
		adj[w].addToHead(v);
	}
	
	/**
	 * 
	 * 删除顶点及其相连的所有边
	 * @param v
	 */
	public void deleteVertex(int v) {
		validateVertex(v);
		// 遍历邻接表，删除与v相关的边
		for (int i = 0; i < adj.length; i++) {
			if(i ==v )
				continue;
			SingleLinkedList list = adj[i];
			for (int j = 0; list !=null && j <  list.size(); j++) {
				if(list.get(j) == v){
					list.deleteNode(j);
					this.e--;
				}
			}
		}
		adj[v] = null;
		int size = this.getV()-1;
		int[] newVertexes = new int[size];
		int i = 0;
		int k = 0;
		while(size > 0){
			if(adj[i]!=null){
				size--;
				newVertexes[k] = i;
				k++;
			}
			i++;
		}
		this.v--;
		this.vertexes = newVertexes;
		
	}


	public void validateVertex(int v2) {
		if(v2 <0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	public int getV(){
		return v;
	}
	
	public String toString(){
		StringBuilder rs = new StringBuilder();
		rs.append(v+"个顶点，"+e+"条边").append(NEWLINE);
		for (int i = 0; i < vertexes.length; i++) {
			rs.append(vertexes[i]+":");
			if(adj[vertexes[i]] == null){
				continue;
			}
			Node head = adj[vertexes[i]].getHead();
			while(head != null){
				rs.append(head.getInfo()).append("->");
				head = head.getNext();
			}
			rs.append(NEWLINE);
		}
		return rs.toString();
	}
	
	public Iterable<Integer> adj(int v){
		return this.adj[v];
	}
	
	
}
class DepthFirstSearchForGraphN{
	private boolean[] marked;
	
	private int count;
	
	public DepthFirstSearchForGraphN(GraphN g){
		marked = new boolean[100];
		
		dfs(g,g.getVertexes()[0]);
	}

	private void dfs(GraphN g, int i) {
		marked[i] = true;
		count++;
		if(g.adj(i) != null){
			for (int j : g.adj(i)) {
				if(!marked[j]){
					dfs(g,j);
				}
			}
		}
	}
	
	public boolean checkWhetherConnected(GraphN g){
		if(count == g.getV())
			return true;
		return false;
	}
}
