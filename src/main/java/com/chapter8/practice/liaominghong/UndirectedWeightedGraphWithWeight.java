package com.chapter8.practice.liaominghong;

/**
 * <p>UndirectedWeightedGraphWithWeight</p>
 * <p>带权重的无向加权图</p>
 * @author yanan
 * @date 2018年6月8日
 */
public class UndirectedWeightedGraphWithWeight {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	/**顶点数量**/
	private int v;
	
	/**边数量**/
	private int e;
	
	public int[][] martix;
	
	public UndirectedWeightedGraphWithWeight(int v) {
		this.v = v;
		this.martix = new int[v][v];
	}
	
	public void addEdge(int v,int w,int weight) {
		validateVertex(v);
		validateVertex(w);
		martix[v][w] = weight;
		martix[w][v] = weight;
		e++;
	}
	
	public boolean hasEdge(int v,int w) {
		validateVertex(v);
		validateVertex(w);
		return martix[v][w] > 0 ? true : false;
	}
	
	public int getWeight(int v,int w) {
		validateVertex(v);
		validateVertex(w);
		return martix[v][w];
	}
	
	public void validateVertex(int v2) {
		if(v2 < 0 || v2 >= this.v)
			throw new IllegalArgumentException("顶点 " + v + " 的范围不在 0 和 " + (v-1)+" 之间！");
	}
	
	public int getV() {
		return this.v;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
        for (int i = 0; i < v; i++) {
            sb.append(i + ": ");
            for (int w = 0; w < martix[i].length;w++) {
                sb.append(i + "->"+w+" : "+martix[i][w]+"  ");
            }
            sb.append(NEWLINE);
        }
		return sb.toString();
	}
	
	public static void main(String[] args) {
		UndirectedWeightedGraphWithWeight g = new UndirectedWeightedGraphWithWeight(6);
		g.addEdge(0,1,6);
		g.addEdge(0,2,1);
		g.addEdge(0,3,5);
		g.addEdge(1,2,5);
		g.addEdge(3,2,5);
		g.addEdge(3,5,2);
		g.addEdge(5,2,4);
		g.addEdge(5,4,6);
		g.addEdge(4,2,6);
		g.addEdge(4,1,3);
		System.out.println(g);
	}
}
