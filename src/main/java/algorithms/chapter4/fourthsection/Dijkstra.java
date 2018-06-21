package algorithms.chapter4.fourthsection;

import java.util.ArrayList;

import com.chapter4.text.StackL;

public class Dijkstra {

	/**已经确定的顶点集合**/
	private ArrayList<Integer> s = new ArrayList<Integer>();
	
	/**尚未确定的顶点集合**/
	private ArrayList<Integer> v_s = new ArrayList<Integer>();
	
	private double[] shortestPath;
	
	private int[] edgeTo;
	
	
	public Dijkstra(DirectedWeightedGraph graph,int start){
		
		shortestPath = new double[graph.getV()];
		this.edgeTo = new int[graph.getV()];
		for (int i = 0; i < edgeTo.length; i++) {
			if(i != start){
				edgeTo[i] = start;
			}
		}
		
		// 将起点加入已经确定的集合中
		s.add(start);
		
		// 去除起点之后的顶点加入v-s集合
		for (int i = 0; i < graph.getV(); i++) {
			if(i != start){
				v_s.add(i);
				shortestPath[i] = graph.getWeight(start, i);
			}
			
		}
		
		// 每一步从v-s集合中选取一个顶点w加入s中，使s中从起点到其余顶点的路径最短
		while(!v_s.isEmpty()){
			int w = getMinWeightVertex();
			s.add(w);
			for (int i = 0; i < v_s.size(); i++) {
				if(v_s.get(i) == w){
					v_s.remove(i);
					break;
				}
				
			}
			for (int i = 0; i < v_s.size(); i++) {
				int v = v_s.get(i);
				if(shortestPath[w] + graph.getWeight(w, v) < shortestPath[v]){
					shortestPath[v] = shortestPath[w] + graph.getWeight(w, v);
					edgeTo[v] = w;
				}
			}
		}
		System.out.printf("顶点%d到各个顶点的最短距离如下\n",start);
		for (int i = 0; i < shortestPath.length; i++) {
			if(i != start){
				System.out.printf("顶点%d到顶点%d的最短距离%9.2f\n",start,i,shortestPath[i]);
			}
		}
	}
	
	public void showAllShortestPathInGraph(DirectedWeightedGraph graph,int start){
		
	}
	
	public void showShortestPathBetweenFromAndTo(DirectedWeightedGraph graph,int start,int end){
		if(start == end)
			throw new IllegalArgumentException(start + "和" + end + "不能相同！");
		StackL<Integer> path = new StackL<Integer>();
		path.push(end);
		while(this.edgeTo[end] != start){
			path.push(this.edgeTo[end]);
			end = this.edgeTo[end];
		}
		path.push(this.edgeTo[end]);
		while(!path.isEmpty()){
			if(path.size() > 1)
				System.out.print(path.pop()+"->");
			else
				System.out.print(path.pop());
		}
	}
	
	private int getMinWeightVertex(){
		int vertex = 0;
		double currenWeight = DirectedWeightedGraph.MAX_WEIGHT;
		for (int i = 0; i < v_s.size(); i++) {
			int k = v_s.get(i);
			if(currenWeight > shortestPath[k]){
				vertex = k;
				currenWeight = shortestPath[k];
			}
		}
		
		return vertex;
	}
	
	public static void main(String[] args) {
		/*DirectedWeightedGraph graph = new DirectedWeightedGraph(6);
		graph.addEdge(0, 2, 10);
		graph.addEdge(0, 4, 30);
		graph.addEdge(0, 5, 100);
		graph.addEdge(1, 2, 5);
		graph.addEdge(2, 3, 50);
		graph.addEdge(3, 5, 10);
		graph.addEdge(4, 3, 20);
		graph.addEdge(4, 5, 60);*/
		DirectedWeightedGraph graph = new DirectedWeightedGraph(5);
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 3, 30);
		graph.addEdge(0, 4, 100);
		graph.addEdge(1, 2, 50);
		graph.addEdge(2, 3, 20);
		graph.addEdge(2, 4, 10);
		graph.addEdge(3, 2, 20);
		graph.addEdge(3, 4, 60);
		System.out.println(graph);
		int start = 0;
		int end = 4;
		Dijkstra d = new Dijkstra(graph, start);
		d.showShortestPathBetweenFromAndTo(graph, start, end);
	}

}
