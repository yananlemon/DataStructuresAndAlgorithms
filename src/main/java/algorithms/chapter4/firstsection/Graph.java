package algorithms.chapter4.firstsection;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.chapter3.text.SingleLinkedList;

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

	private SingleLinkedList[] adj;
	
	private ArrayList<Integer[]> edges = new ArrayList<Integer[]>();

	/**
	 * 根据{@code v}初始化一个空的图对象。
	 * @param v 图中顶点的数量
	 * @throws IllegalArgumentException 如果 {@code V < 0}
	 */
	public Graph(int v){
		if(v < 0)
			throw new IllegalArgumentException("顶点的数量不能是负数！");
		this.v = v;
		this.adj = new SingleLinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new SingleLinkedList();
		}
	}
	
	/**
	 * 
	 * 从输入流中创建图对象
	 * 输入流格式如下
	 * 5 6(V,E)
	 * 0,1
	 * 3,4
	 * ...
	 * @param in
	 */
	public Graph(InputStream in){
		byte[] data = new byte[1024];
		int i = -1;
		StringBuilder sb = new StringBuilder();
		try{
			while((i=in.read(data,0,data.length)) != -1){
				String str = new String(data,0,i,"UTF-8");
				sb.append(str);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] info = sb.toString().split(" |\r\n");
		this.v = Integer.parseInt(info[0]);
		this.adj = new SingleLinkedList[v];
		for (int k = 0; k < v; k++) {
			adj[k] = new SingleLinkedList();
		}
		int index = 2;
		for (int k = 0; k < Integer.parseInt(info[1]); k++) {
			String[] edge = info[index++].split(",");
			Integer v = Integer.parseInt(edge[0]);
			Integer w = Integer.parseInt(edge[1]);
			addEdge(v, w);
		}
	}
	
	public Graph(BufferedReader reader) throws IOException{
		StringBuilder sb = new StringBuilder();
		try {
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line).append(NEWLINE);
			}
		} catch (IOException e) {
			throw e;
		}
		String[] info = sb.toString().split("\r\n");
		this.v = Integer.parseInt(info[0]);
		this.adj = new SingleLinkedList[v];
		this.e = Integer.parseInt(info[1]);
		for (int k = 0; k < v; k++) {
			adj[k] = new SingleLinkedList();
		}
		for (int k = 2; k < info.length; k++) {
			String[] vAndAdj = info[k].split(" ");
			for (int i = 0; i < vAndAdj.length - 1 ; i++) {
				addEdge(Integer.parseInt(vAndAdj[0]), Integer.parseInt(vAndAdj[i+1]));
			}
			
		}
		System.out.println(this);
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
		Integer[] edge = new Integer[2];
		edge[0] = v;
		edge[1] =w;
		edges.add(edge);
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
	
	public ArrayList<Integer[]> getEdges() {
		return edges;
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
		/*Graph g = new Graph(5);
		g.addEdge(0,1);
		g.addEdge(0,3);
		g.addEdge(1,2);
		g.addEdge(1,4);
		g.addEdge(2,3);
		g.addEdge(2,4);*/
		/*Graph g = new Graph(12);
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
		System.out.println(g.hasEdge(5, 2));
		System.out.println(g.hasEdge(10, 2));
		System.out.println(g.hasEdge(10, 23));*/
		/*if(args == null || args.length != 1){
			System.out.println("Useage:Graph D:\\Graph\\Graph.txt");
		}
		try {
			Graph g = new Graph(new FileInputStream(new File(args[0])));
			System.out.println(g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		try {
			Graph g = new Graph(new BufferedReader(new FileReader(new File("D:\\Graph\\Graph.txt"))));
			System.out.println(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
