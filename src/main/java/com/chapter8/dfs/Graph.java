package com.chapter8.dfs;

/**
 * 
 * @author andy
 *
 */
public class Graph {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**图中顶点的数量**/
	private final int v;

	/**图中边的数量**/
	private int e;

	private Bag<Integer>[] adj;

	/**
	 * 根据{@code v}初始化一个空的图对象。
	 * @param v 图中顶点的数量
	 * @throws IllegalArgumentException 如果 {@code V < 0}
	 */
	public Graph(int v){
		if(v < 0)
			throw new IllegalArgumentException("顶点的数量不能是负数！");
		this.v = v;
		this.adj = new Bag[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new Bag<Integer>();
		}
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
		adj[v].add(w);
		adj[w].add(v);
	}


	private void validateVertex(int v2) {
		if(v2 <0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	/**
	 * 返回顶点{@code v}的邻接表
	 * @param v 顶点
	 * @return 顶点{@code v}的邻接表
	 */
	public Iterable<Integer> adj(int v){
		return this.adj(v);
	}
	
	/**
     * 返回顶点 {@code v}的度.
     *
     * @param  v 顶点
     * @return 顶点 {@code v}的度
     * @throws IllegalArgumentException 除非 {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
    
    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
        for (int i = 0; i < v; i++) {
            s.append(i + ": ");
            for (int w : adj[i]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

	public static void main(String[] args) {
		/*Graph g = new Graph(5);
		g.addEdge(0,1);
		g.addEdge(0,3);
		g.addEdge(1,2);
		g.addEdge(1,4);
		g.addEdge(2,3);
		g.addEdge(2,4);*/
		Graph g = new Graph(12);
		g.addEdge(8,4);
		g.addEdge(2,3);
		g.addEdge(1,11);
		g.addEdge(0,6);
		g.addEdge(3,6);
		g.addEdge(10,3);
		g.addEdge(7,11);
		g.addEdge(7,8);
		g.addEdge(11,8);
		g.addEdge(2,0);
		g.addEdge(6,2);
		g.addEdge(5,2);
		g.addEdge(5,10);
		g.addEdge(5,0);
		g.addEdge(8,1);
		g.addEdge(4,1);
		
		System.out.println(g);
	}

}
