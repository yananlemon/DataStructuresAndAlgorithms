package com.chapter8.practice.liaominghong;

import java.util.ArrayList;

/**
 * <p>PrimWithAdjacencyMatrix</p>
 * <p>Prim算法——邻接矩阵版本</p>
 * @author yanan
 * @date 2018年6月8日
 */
public class PrimWithAdjacencyMatrix {
	
	private UndirectedWeightedGraphWithWeight graph;
	
	/**起始顶点**/
	private int start;
	
	/**用于存储加权无向图的最小生成树的顶点**/
	private ArrayList<Integer> vertexes = new ArrayList<Integer>();
	
	/**用于存储加权无向图中除了最小生成树的顶点之外的顶点**/
	private ArrayList<Integer> 	others  = new ArrayList<Integer>();
	
	private double totalWeight;
	
	/****/
	private int[][] t;

	public  PrimWithAdjacencyMatrix(UndirectedWeightedGraphWithWeight graph,int start) throws Exception {
		this.graph = graph;
		this.t = new int[graph.getV()][graph.getV()];
		this.start = start;
		vertexes.add(start);
		// others集合中包含除去起始顶点的所有顶点
		for (int i = 0; i < graph.getV(); i++) {
			if(i != start)
				others.add(i);
		}
		generateMixTree(vertexes, others);
	}
	
	private void generateMixTree(ArrayList<Integer> u, ArrayList<Integer> v) throws Exception {
		if(v == null || v.size() <= 0)
			return;
		int t = findMinWeightWithVertex(u,v);
		u.add(t);
		for (int i = 0; i < v.size(); i++) {
			if(v.get(i) == t)
				v.remove(i);
		}
		generateMixTree(u, v);
	}
	
	/**
	 * 寻找一个顶点{@code rs}并将其加入到最小生成树顶点集合中
	 * 该顶点具有这样的性质：循环{@code u}中所有顶点，并依次判断这些顶点与{@code v}中
	 * 所有顶点是否存在边并且比较最小权重，最小的权重的边的顶点即为{@code rs}
	 * @return {@code rs}
	 * @throws Exception 
	 */
	private int findMinWeightWithVertex(ArrayList<Integer> u,ArrayList<Integer> v) throws Exception{
		int weight = 99999;
		int rs = -1;
		int v1 = -1;
		for (int i = 0; i < u.size(); i++) {
			for (int k = 0; k < v.size(); k++) {
				if(graph.hasEdge(u.get(i), v.get(k)) ) {
					int edgeWeight = graph.getWeight(u.get(i), v.get(k));
					if(edgeWeight < weight) {
						weight = edgeWeight;
						rs = v.get(k);//更新顶点
						v1 = u.get(i);
					}
				}
			}
		}
		totalWeight += weight;
		this.t[v1][rs] = weight;
		this.t[rs][v1] = weight;
		return rs;
	}

	public static void main(String[] args) {
		/*UndirectedWeightedGraphWithWeight g = new UndirectedWeightedGraphWithWeight(6);
		g.addEdge(0,1,6);
		g.addEdge(0,2,1);
		g.addEdge(0,3,5);
		g.addEdge(1,2,5);
		g.addEdge(3,2,5);
		g.addEdge(3,5,2);
		g.addEdge(5,2,4);
		g.addEdge(5,4,6);
		g.addEdge(4,2,6);
		g.addEdge(4,1,3);*/
		UndirectedWeightedGraphWithWeight g = new UndirectedWeightedGraphWithWeight(7);
		g.addEdge(0,1,12 );
		g.addEdge(1,2,10 );
		g.addEdge(2,3,3 );
		g.addEdge(3,4,4 );
		g.addEdge(4,6,8 );
		g.addEdge(6,0,14 );
		g.addEdge(0,5,16 );
		g.addEdge(1,5,7 );
		g.addEdge(5,2,6 );
		g.addEdge(2,4,5 );
		g.addEdge(5,4,2 );
		g.addEdge(6,5,9 );
		System.out.println(g);
		/*for (int i = 0; i < g.martix.length; i++) {
			for (int k = 0; k < g.martix[i].length; k++) {
				//System.out.printf("%d %d",i,prim.t[i][k]);
				System.out.printf("%d,",g.martix[i][k]);
			}
			System.out.println();
		}*/
		System.out.println();
		PrimWithAdjacencyMatrix prim;
		try {
			prim = new PrimWithAdjacencyMatrix(g, 0);
			System.out.println("最小生成树顶点集合："+prim.vertexes);
			for (int i = 0; i < prim.t.length; i++) {
				for (int k = 0; k < prim.t[i].length; k++) {
					//System.out.printf("%d %d",i,prim.t[i][k]);
					System.out.printf("%d,",prim.t[i][k]);
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
