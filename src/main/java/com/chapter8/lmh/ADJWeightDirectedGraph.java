package com.chapter8.lmh;

import java.util.HashMap;

import com.chapter3.text.DoublyLinkedList;

/**
 * <p>ADJWeightDirectedGraph</p>  
 * <p>带权有向图之邻接表实现</p>  
 * @author yanan  
 * @date 2018年7月13日
 */
public class ADJWeightDirectedGraph {
	private static final String NEWLINE = System.getProperty("line.separator"); // 换行符
	private int v;																// 顶点数量
	private int e;																// 边数
	private HashMap<String,Integer> vertexesMap;								// 顶点集合Map
	private DoublyLinkedList<Vertex>[] adj;										// 邻接表
	private int index;

	public ADJWeightDirectedGraph(int v){
		this.v = v;
		this.vertexesMap = new HashMap<String,Integer>();
		this.adj = new DoublyLinkedList[v];
		for (int i = 0; i < adj.length; i++) {
			this.adj[i] = new DoublyLinkedList<Vertex>();
		}
	}

	/**
	 * 根据{@code v1},{@code v2}以及{@code weight}添加一条有向边  
	 * @param v1
	 * @param v2
	 * @param weight
	 */
	public void addEdge(String v1,String v2,double weight){
		Vertex vertex = null;
		if(vertexesMap.get(v1) == null){
			vertexesMap.put(v1, index++);
		}
		if(vertexesMap.get(v2) == null){
			vertexesMap.put(v2, index++);
		}
		vertex = new Vertex(index, v2, weight);
		this.adj[vertexesMap.get(v1)].addToHead(vertex);
		this.e++;
	}

	public String graph() {
		StringBuilder s = new StringBuilder();
		s.append(v + " 个顶点, " + e + " 条边 " + NEWLINE);
		for (String  v: vertexesMap.keySet()) {
			s.append("顶点"+v + ": ");
			int i = vertexesMap.get(v);
			DoublyLinkedList<Vertex> list = adj[i];
			for (int j = 0; j < list.size(); j++) {
				Vertex v1 = list.get(j);
				s.append(v + "->" + v1.getName()+ "," + v1.getWeight() + " ; ");
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
		ADJWeightDirectedGraph graph = new ADJWeightDirectedGraph(6);
		graph.addEdge("a", "b", 3);
		graph.addEdge("b", "c", 1);
		graph.addEdge("c", "d", 2);
		graph.addEdge("d", "b", 3);
		graph.addEdge("e", "d", 3);
		graph.addEdge("f", "d", 2);
		graph.addEdge("e", "f", 2);
		graph.addEdge("a", "d", 4);
		graph.addEdge("a", "f", 5);
		graph.addEdge("b", "f", 1);
		System.out.println(graph);
	}

}

