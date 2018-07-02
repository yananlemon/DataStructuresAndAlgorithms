package algorithms.chapter4.fourthsection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.chapter4.text.StackL;

public class Dijkstra {

	/**已经确定的顶点集合**/
	private ArrayList<Integer> knownList = new ArrayList<Integer>();
	
	/**尚未确定的顶点集合**/
	private Set<Integer> unknownList = new HashSet<Integer>();
	
	/**保存最短路径的数组**/
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
		knownList.add(start);
		
		// 去除起点之后的顶点加入v-s集合
		for (int i = 0; i < graph.getV(); i++) {
			if(i != start){
				unknownList.add(i);
				shortestPath[i] = graph.getWeight(start, i);
			}
			
		}
		
		// 每一步从待选顶点集合中选取一个顶点w加入s中，使s中从起点到其余顶点的路径最短
		while(!unknownList.isEmpty()){
			int w = getMinWeightVertex();
			knownList.add(w);
			unknownList.remove(w);
			for (Integer v : unknownList) {
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
		for (int i = 0; i < graph.getV(); i++) {
			if( i != start)
				showShortestPathBetweenFromAndTo(graph, start, i);
		}
	}
	
	public void showShortestPathBetweenFromAndTo(DirectedWeightedGraph graph,int start,int end){
		if(start == end)
			throw new IllegalArgumentException(start + "和" + end + "不能相同！");
		StackL<Integer> path = new StackL<Integer>();
		path.push(end);
		System.out.printf("%d到%d的最短路径如下\n",start,end);
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
		System.out.println();
	}
	
	private int getMinWeightVertex(){
		int vertex = 0;
		double currenWeight = DirectedWeightedGraph.MAX_WEIGHT;
		for (Integer i : unknownList) {
			if(currenWeight > shortestPath[i]){
				vertex = i;
				currenWeight = shortestPath[i];
			}
		}
		
		return vertex;
	}
	
	public static void main(String[] args) {
		/*DirectedWeightedGraph graph = new DirectedWeightedGraph(6);
		graph.addEdge(0, 2, 15);
		graph.addEdge(0, 1, 20);
		graph.addEdge(1, 4, 10);
		graph.addEdge(1, 5, 30);
		graph.addEdge(2, 1, 4);
		graph.addEdge(2, 5, 10);
		graph.addEdge(4, 3, 15);
		graph.addEdge(5, 4, 10);
		graph.addEdge(5, 3, 4);*/
		/*DirectedWeightedGraph graph = new DirectedWeightedGraph(5);
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 3, 30);
		graph.addEdge(0, 4, 100);
		graph.addEdge(1, 2, 50);
		graph.addEdge(2, 3, 20);
		graph.addEdge(2, 4, 10);
		graph.addEdge(3, 2, 20);
		graph.addEdge(3, 4, 60);*/
		DirectedWeightedGraph graph = new DirectedWeightedGraph(3);
		graph.addEdge(0, 1, 2);
		graph.addEdge(0, 2, 3);
		graph.addEdge(2, 1, -2);
		System.out.println(graph);
		int start = 0;
		int end = 1;
		Dijkstra d = new Dijkstra(graph, start);
		d.showShortestPathBetweenFromAndTo(graph, start, end);
		d.showAllShortestPathInGraph(graph, start);
	}

}
