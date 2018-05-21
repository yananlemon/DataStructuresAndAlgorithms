package com.chapter8.firstsection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>Graph</p>
 * <p>图的邻接矩阵表示方法</p>
 * @author yanan
 * @date 2018年5月16日 
 */
public class MTGraph {
	
	private static final int MAX_SIZE_OF_VERTEX = 100;
	
	/*顶点数*/
	private int n;
	
	/*边数*/
	private int e;
	
	/*顶点集合*/
	private String[] vertex = new String[MAX_SIZE_OF_VERTEX];
	
	/*各个顶点之间边的关系*/
	private int[][] edge = new int[MAX_SIZE_OF_VERTEX][MAX_SIZE_OF_VERTEX];
	
	public MTGraph(){
		
	}
	
	public MTGraph createGraph() {
		MTGraph graph = new MTGraph();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入图的顶点数量\n");
		graph.n = in.nextInt();
		System.out.println("请输入图的边数\n");
		graph.e = in.nextInt();
		System.out.println("请每次输入一个顶点名称，并按回车键\n");
		
		// 建立顶点表
		for (int i = 0; i < graph.n; i++) {
			graph.vertex[i] = in.next();
		}
		
		// 初始化邻接矩阵
		for (int i = 0; i < graph.n; i++) {
			for (int j = 0; j < graph.n; j++) {
				graph.edge[i][j] = 0;
			}
		}
		
		System.out.println("请输入每条边对应的两个顶点的索引并以逗号分隔(顶点i的索引,顶点j的索引)，并按回车键\n");
		// 建立邻接矩阵
		for (int i = 0; i < graph.e; i++) {
			String str = in.next();
			String[] strs = str.split(",");
			int rowIndex = Integer.parseInt(strs[0]);
			int colIndex = Integer.parseInt(strs[1]);
			graph.edge[rowIndex][colIndex] = 1;
			graph.edge[colIndex][rowIndex] = 1;
		}
		
		System.out.println("图已经成功建立！");
		System.out.println("下面是该图邻接矩阵：");
		for (int i = 0; i < graph.n; i++) {
			for (int k = 0; k < graph.n; k++) {
				System.out.print(graph.edge[i][k]+" ");
			}
			System.out.println();
		}
		return graph;
	}
	
	
	public static void main(String[] args) {
		MTGraph g = new MTGraph();
		g.createGraph();
	}
}
