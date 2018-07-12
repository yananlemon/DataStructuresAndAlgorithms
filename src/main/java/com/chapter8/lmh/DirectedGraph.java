package com.chapter8.lmh;

import com.chapter3.text.SingleLinkedList;

/**
 * <p>DirectedGraph</p>  
 * <p>有向图</p>  
 * @author yanan  
 * @date 2018年7月12日
 */
public class DirectedGraph {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**图中顶点的数量**/
	private final int v;

	/**图中边的数量**/
	private int e;

	private SingleLinkedList[] adj;
	
	/**
	 * 根据{@code v}初始化一个空的有向图对象。
	 * @param v 图中顶点的数量
	 * @throws IllegalArgumentException 如果 {@code V < 0}
	 */
	public DirectedGraph(int v){
		if(v < 0)
			throw new IllegalArgumentException("顶点的数量不能是负数！");
		this.v = v;
		this.adj = new SingleLinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new SingleLinkedList();
		}
	}
	
	/**
	 * 添加边到有向图。
	 *
	 * @param  v 图中的一个顶点
	 * @param  w 图中的另一个顶点
	 * @throws IllegalArgumentException 除非不满足这两个条件 {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[v].addToHead(w);
		e++;
	}
	
	/**
	 * 
	 * 删除顶点及其相连的所有边
	 * @param v
	 */
	public void deleteVertex(int v) {
		validateVertex(v);
		this.adj[v] = null;
	}
	
	public void deleteEdge(int v,int w) {
		validateVertex(v);
		validateVertex(w);
		e--;
		for (int i = 0; i < adj[v].size(); i++) {
			if(adj[v].get(i) == w) {
				adj[v].deleteNode(i);
				break;
			}
		}
		
		for (int i = 0; i < adj[w].size(); i++) {
			if(adj[w].get(i) == v) {
				adj[w].deleteNode(i);
				break;
			}
		}
	}


	public void validateVertex(int v2) {
		if(v2 <0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	public int getV(){
		return v;
	}
	
	public int getE(){
		return e;
	}

	/**
	 * 返回顶点{@code v}的邻接表
	 * @param v 顶点
	 * @return 顶点{@code v}的邻接表
	 */
	public Iterable<Integer> adj(int v){
		Iterable<Integer> it = this.adj[v];
		return it;
	}
	
	/**
     * 返回顶点 {@code v}的入度.
     *
     * @param  v 顶点
     * @return 顶点 {@code v}的度
     * @throws IllegalArgumentException 除非 {@code 0 <= v < V}
     */
    public int inputDegree(int v) {
        validateVertex(v);
        int iDegeree = 0;
        for (int i = 0; i < adj.length; i++) {
			SingleLinkedList list = adj[i];
			for (int j = 0; j < list.size(); j++) {
				if(list.get(j) == v)
					iDegeree++;
				
			}
			
		}
        return iDegeree;
    }
    
    /**
     * 返回顶点 {@code v}的入度.
     *
     * @param  v 顶点
     * @return 顶点 {@code v}的度
     * @throws IllegalArgumentException 除非 {@code 0 <= v < V}
     */
    public int outputDegree(int v) {
        validateVertex(v);
        
        return this.adj[v].size();
    }
    
    /**
     * 检测顶点{@code v}和顶点{@code w}之间是否有边
     * @param v
     * @param w
     * @return true:有边;false:无边
     */
    public boolean hasEdge(int v,int w) {
    	validateVertex(v);
    	validateVertex(w);
    	for(int i:adj[v]) {
    		if(i == w)
    			return true;
    	}
    	return false;
    }
    
    
    
    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String graph() {
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
    
    

	@Override
	public String toString() {
		return graph();
	}
	
	public static void main(String[] args) {
		DirectedGraph dGraph = new DirectedGraph(6);
		dGraph.addEdge(0, 1);
		dGraph.addEdge(1, 4);
		dGraph.addEdge(2, 3);
		dGraph.addEdge(3, 1);
		dGraph.addEdge(3, 4);
		dGraph.addEdge(4, 0);
		dGraph.addEdge(4, 5);
		dGraph.addEdge(5, 2);
		System.out.println(dGraph);
		for (int i = 0; i < dGraph.getV(); i++) {
			System.out.printf("顶点%d的出度是%d,入度是%d\n",i,dGraph.outputDegree(i),dGraph.inputDegree(i));
		}
		
	}

}
