package algorithms.chapter4.thirdsection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.chapter3.text.DoublyLinkedList;

/**
 * Kruskal算法：最小生成树
 * @author yanan
 *
 */
public class Kruskal {
	
	private GraphWithWeight graph;
	
	public Kruskal(GraphWithWeight graph) {
		this.graph = graph;
		s = new int[graph.getV()];
		for (int i = 0; i < s.length; i++) {
			s[i] = i;
		}
	}
	public static void main(String[] args) {
		GraphWithWeight g = new GraphWithWeight(6);
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
		Kruskal k = new Kruskal(g);
		k.mst();
	}
	
	public GraphWithWeight mst() {
		GraphWithWeight graph = new GraphWithWeight(this.graph.getV());
		ArrayList<Edge> sortedEdges = sortEdges();
		System.out.println(sortedEdges);
		int index = 0;
		// 将边加入到图中，直到图中有n-1条边
		while(graph.getE() != this.graph.getV() - 1) {
			Edge edge = sortedEdges.get(index++);
			System.out.println(edge);
			int v = find(edge.getV());
			int w = find(edge.getW());
			if( v != w) {
				graph.addEdge(edge.getV(),edge.getW(),edge.getWeight());
				union(v, w);
			}
		}
		System.out.println("Kruskal:"+graph);
		return graph;
	}
	
	/*private int find(int x) {
		int f = x;
		while(s[f] > 0)
			f = s[f];
		return f;
	}*/
	
	private int find(int x) {
		if(s[x] == x)
			return x;
		else
			return find(s[x]);
	}
	
	private void union(int v,int w) {
		s[v] = w;
	}
	
	private int[] s;
	
	private ArrayList<Edge> sortEdges(){
		ArrayList<Edge> rs = new ArrayList<Edge>();
		ArrayList<Edge> rs1 = new ArrayList<Edge>();
		DoublyLinkedList<Edge>[] adj = this.graph.getAdj();
		Set<Edge> sets = new HashSet<Edge>();
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].size(); j++) {
				Edge other = adj[i].get(j);
				rs.add(other);
				
			}
		}
		for(int i = 0; i < rs.size(); i++) {
			Edge e = rs.get(i);
			for(int k = i; k < rs.size(); k++) {
				Edge other = rs.get(k);
				if (e.getV() == other.getW() && e.getW() == other.getV() && 
						Double.doubleToLongBits(e.getWeight()) == Double.doubleToLongBits(other.getWeight())) {
					rs1.add(e);
				}
			}
		}
		rs.removeAll(rs1);
		for(Edge e : sets)
			rs.add(e);
		Collections.sort(rs,new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				if(o1.getWeight() > o2.getWeight())
					return 1;
				if(o1.getWeight() < o2.getWeight())
					return -1;
				return 0;
			}
		});
		return rs;
	}

}
