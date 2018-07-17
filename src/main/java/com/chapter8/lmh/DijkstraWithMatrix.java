package com.chapter8.lmh;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.chapter4.text.StackL;

/**
 * <p>Dijkstar算法基于邻接矩阵实现</p>
 * @author andy
 * @date 2018/7/17
 * @see com.chapter8.lmh.MatrixGraph
 */
public class DijkstraWithMatrix {

	/**已经确定的顶点集合**/
	private ArrayList<Integer> knownList = new ArrayList<Integer>();
	
	/**尚未确定的顶点集合**/
	private Set<Integer> unknownList = new HashSet<Integer>();
	
	/**保存最短路径的数组**/
	private double[] shortestPath;
	
	private int[] edgeTo;
	
	
	public DijkstraWithMatrix(MatrixGraph graph,int start){
		
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
				System.out.printf("顶点%d到顶点%d的最短距离%9.2f\n",start+1,i+1,shortestPath[i]);
			}
		}
	}

	public void showAllShortestPathInGraph(MatrixGraph graph,int start){
		for (int i = 0; i < graph.getV(); i++) {
			if( i != start)
				showShortestPathBetweenFromAndTo(graph, start, i);
		}
	}
	
	public void showShortestPathBetweenFromAndTo(MatrixGraph graph,int start,int end){
		if(start == end)
			throw new IllegalArgumentException(start + "和" + end + "不能相同！");
		StackL<Integer> path = new StackL<Integer>();
		path.push(end+1);
		System.out.printf("%d到%d的最短路径如下\n",start+1,end+1);
		while(this.edgeTo[end] != start){
			path.push(this.edgeTo[end]+1);
			end = this.edgeTo[end];
		}
		path.push(this.edgeTo[end]+1);
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
		double currenWeight = MatrixGraph.MAX_WEIGHT;
		for (Integer i : unknownList) {
			if(currenWeight > shortestPath[i]){
				vertex = i;
				currenWeight = shortestPath[i];
			}
		}
		
		return vertex;
	}
	
	public static void main(String[] args) {
		try {
			MatrixGraph graph = new MatrixGraph();
			graph.create(new FileInputStream(new File("script\\Graph\\DirectedWeightGraph.txt")));
			System.out.println(graph);
			int start = 0;
			DijkstraWithMatrix d = new DijkstraWithMatrix(graph, start);
			d.showAllShortestPathInGraph(graph, start);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
