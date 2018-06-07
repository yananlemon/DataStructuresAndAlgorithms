package algorithms.chapter4.thirdsection;

import java.util.ArrayList;

import com.chapter3.text.DoublyLinkedList;

public class Prim {
	
	/**起始顶点**/
	private int start;
	
	/**用于存储加权无向图的最小生成树的顶点**/
	private ArrayList<Integer> vertexes = new ArrayList<Integer>();
	
	private ArrayList<Integer> others = new ArrayList<Integer>();
	
	private GraphWithWeight graph;
	
	private boolean[] marked;
	
	public Prim(GraphWithWeight g , int v) throws Exception{
		this.start = v;
		this.graph = g;
		marked = new boolean[g.getV()];
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
	
	private void generateMixTree(ArrayList<Integer> u,ArrayList<Integer> v) throws Exception{
		if(v == null || v.size() <= 0)
			return;
		int t = findMixWeightWithVertex(u);
		u.add(t);
		for (int i = 0; i < v.size(); i++) {
			if(v.get(i) == t)
				v.remove(i);
		}
		generateMixTree(u, v);
	}
	
	/**
	 * 寻找保存最小生成树顶点集合中所有顶点关联的边的权重最小的顶点
	 * @return
	 * @throws Exception 
	 */
	private int findMixWeightWithVertex(ArrayList<Integer> u) throws Exception{
		double weight = 99999;
		int v = -1;
		for (int i = 0; i < u.size(); i++) {
			if(!marked[u.get(i)]){
				marked[u.get(i)] = true;
				DoublyLinkedList<Edge> adj = graph.adj(u.get(i));
				for (int j = 0; j < adj.size(); j++) {
					Edge edge = adj.get(j);
					if(edge.getWeight() < weight && !marked[edge.getOther(u.get(i))]){
						weight = edge.getWeight();
						v = edge.getOther(u.get(i));//更新顶点
					}
				}
			}
		}
		return v;
	}
	
	public static void main(String[] args) throws Exception {
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
		Prim p = new Prim(g, 0);
		System.out.println(p.vertexes);
	}
}
