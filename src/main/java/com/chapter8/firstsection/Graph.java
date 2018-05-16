package com.chapter8.firstsection;

public class Graph {
	
	/*顶点集合*/
	private String[] vertex = new String[5];
	
	/*各个顶点之间的邻接关系*/
	private int[][] edge = new int[5][5];
	
	public Graph(){
		vertex[0] = "V1";
		vertex[1] = "V2";
		vertex[2] = "V3";
		vertex[3] = "V4";
		vertex[4] = "V5";
		edge[0][0] =  0;
		edge[0][1] =  1;
		edge[0][2] =  0;
		edge[0][3] =  1;
		edge[0][4] =  0;
		
		edge[1][0] =  1;
		edge[1][1] =  0;
		edge[1][2] =  1;
		edge[1][3] =  0;
		edge[1][4] =  1;
		
		edge[2][0] =  0;
		edge[2][1] =  1;
		edge[2][2] =  0;
		edge[2][3] =  1;
		edge[2][4] =  1;
		
		edge[3][0] =  1;
		edge[3][1] =  0;
		edge[3][2] =  1;
		edge[3][3] =  0;
		edge[3][4] =  0;
		
		edge[4][0] =  0;
		edge[4][1] =  1;
		edge[4][2] =  1;
		edge[4][3] =  0;
		edge[4][4] =  0;
	}
	
	/**
	 * 求顶点v的度
	 * @param graph
	 * @param v
	 * @return
	 */
	int test(Graph graph,String v){
		// 首先检查顶点在图中是否存在
		int index = checkVertexWhetherExist(graph, v);
		if(index == -1)
			return -1;
		int rs = 0;
		for (int i = 0; i < graph.edge[index].length; i++) {
			if(graph.edge[index][i] == 1)
				rs ++ ;
		}
		return rs;
	}
	
	int checkVertexWhetherExist(Graph graph,String v){
		int index = 0;
		for (; index < graph.vertex.length; index++) {
			if(graph.vertex[index].equals(v))
				return index;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		String v = "V12";
		int rs = graph.test(graph, v);
		System.out.printf("顶点%s的度是%d\n",v,rs);
	}
}
