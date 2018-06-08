package algorithms.chapter4.thirdsection;

import java.util.ArrayList;


public class Prim {
	
	/**起始顶点**/
	private int start;
	
	/**用于存储加权无向图的最小生成树的顶点**/
	private ArrayList<Integer> vertexes = new ArrayList<Integer>();
	
	/**用于存储加权无向图中除了最小生成树的顶点之外的顶点**/
	private ArrayList<Integer> 	others  = new ArrayList<Integer>();
	
	/**用于存储加权无向图最小生成树的边集**/
	private ArrayList<Edge> 	edges   = new ArrayList<Edge>();
	
	private GraphWithWeight graph;
	
	private double totalWeight;
	
	public Prim(GraphWithWeight g , int v) throws Exception{
		this.start = v;
		this.graph = g;
		System.out.printf("Prim,起始顶点是%d\n",start);
		
		// 初始情况最小生成树的顶点集合中只包含起始顶点
		vertexes.add(this.start);
		
		// others集合中包含除去起始顶点的所有顶点
		for (int i = 0; i < g.getV(); i++) {
			if(i != v)
				others.add(i);
		}
		generateMixTree(vertexes, others);
	}
	/**
	 * 使用递归生成一幅连通图的最小生成树
	 * @param u
	 * @param v
	 * @throws Exception
	 */
	private void generateMixTree(ArrayList<Integer> u,ArrayList<Integer> v) throws Exception{
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
		double weight = 99999;
		int rs = -1;
		int v1 = -1;
		for (int i = 0; i < u.size(); i++) {
			for (int k = 0; k < v.size(); k++) {
				if(graph.hasEdge(u.get(i), v.get(k)) ) {
					double edgeWeight = graph.getWeight(u.get(i), v.get(k));
					if(edgeWeight < weight) {
						weight = edgeWeight;
						rs = v.get(k);//更新顶点
						v1 = u.get(i);
					}
				}
			}
		}
		totalWeight += weight;
		Edge edge = new Edge(v1, rs, weight);
		this.edges.add(edge);
		return rs;
	}
	
	public double calWeightOfMixTree() {
		double rs = 0;
		if(vertexes == null || vertexes.size() <=0)
			return rs;
		for (int i = 0; i < vertexes.size()-1; i++) {
			rs += graph.getWeight(vertexes.get(i), vertexes.get(i+1));
		}
		return rs;
	}
	
	public static void main(String[] args) throws Exception {
		/*GraphWithWeight g = new GraphWithWeight(7);
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
		g.addEdge(6,5,9 );*/
		/*GraphWithWeight g = new GraphWithWeight(6);
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
		GraphWithWeight g = new GraphWithWeight(8);
		g.addEdge(0,1,2);
		g.addEdge(0,2,3);
		g.addEdge(1,3,2);
		g.addEdge(2,3,1);
		g.addEdge(3,4,2);
		g.addEdge(3,5,4);
		g.addEdge(4,5,1);
		g.addEdge(4,6,2);
		g.addEdge(6,7,3);
		g.addEdge(7,5,1);
		g.addEdge(5,6,2);
		
		System.out.println(g);
		Prim p = new Prim(g, 0);
		System.out.println("最小生成树的顶点是："+p.vertexes);
		System.out.println("最小生成树的边是："+p.edges);

		System.out.println("权重是："+p.totalWeight);
	}
}
