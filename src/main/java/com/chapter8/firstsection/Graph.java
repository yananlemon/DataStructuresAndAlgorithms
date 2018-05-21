package com.chapter8.firstsection;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Graph</p>
 * <p>图的邻接矩阵表示方法</p>
 * @author yanan
 * @date 2018年5月16日 
 */
public class Graph {
	
	private static final int MAX_SIZE_OF_VERTEX = 100;
	
	/*顶点集合*/
	private String[] vertex = new String[MAX_SIZE_OF_VERTEX];
	
	/*各个顶点之间边的关系*/
	private int[][] edge = new int[MAX_SIZE_OF_VERTEX][MAX_SIZE_OF_VERTEX];
	
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
	int getDegreeOfVertex(Graph graph,String v){
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
	
	/**
	 * 判断两个顶点之间是否存在边，算法如下：
	 * 获取两个顶点所在的一维数组的索引，然后判断edge[rowIndex][colIndex] 是否等于1
	 * @param graph
	 * @param v1
	 * @param v2
	 * @return
	 */
	boolean checkEdgeWhetherExistWithVertex(Graph graph,String v1,String v2) {
		int rowIndex = checkVertexWhetherExist(graph, v1);
		int colIndex = checkVertexWhetherExist(graph, v2);
		if(rowIndex == -1 || colIndex == -1)
			return false;
		return edge[rowIndex][colIndex] == 1 ? true : false;
	}
	
	/**
	 * 获取顶点V的所有邻接点
	 * @param graph
	 * @param v
	 * @return
	 */
	List<String> getAdjacentByVertex(Graph graph,String v){
		List<String> rs = new ArrayList<String>();
		int index = checkVertexWhetherExist(graph, v);
		if(index == -1)
			return null;
		for (int i = 0; i < graph.edge[index].length; i++) {
			if(graph.edge[index][i] == 1)
				rs.add(graph.vertex[i]);
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
		String v = "V3";
		int rs = graph.getDegreeOfVertex(graph, v);
		System.out.printf("顶点%s的度是%d\n",v,rs);
		String v2 = "V1";
		System.out.printf("顶点%s和顶点%s之间是否存在边？%b\n",v,v2,graph.checkEdgeWhetherExistWithVertex(graph, v, v2));
		System.out.println("顶点"+v2+"的所有邻接点是："+graph.getAdjacentByVertex(graph, v2));
	}
}
