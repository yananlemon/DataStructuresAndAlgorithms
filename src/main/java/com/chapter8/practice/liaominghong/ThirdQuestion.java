package com.chapter8.practice.liaominghong;

import java.util.Scanner;

/**
 * <p>ThirdQuestion</p>
 * <p>习题第三题</p>
 * @author yanan
 * @date 2018年5月22日 
 */
public class ThirdQuestion {

	public static void main(String[] args) {
		// 使用邻接表创建图
		Graph adjList = new AdjacencyListGraph();
		/*
		 E:
		 	V0,V1
		  	V0,V3
			V1,V2
			V1,V4
			V2,V3
			V2,V4
		 */
		Graph created = adjList.createGraph();
		adjList.showGraph(created);
		
		// 使用邻接矩阵创建图
		/*Graph adjList = new AdjacencyMatrixGraph();
		Graph created = adjList.createGraph();//E:{(0,1),(0,3),(1,2),(1,4),(2,3),(2,4),(3,4)}
		adjList.showGraph(created);*/
	}
	

}

abstract class Graph{
	protected int n;//顶点数量
	protected int e;//边数
	public abstract Graph createGraph();
	public abstract void showGraph(Graph g);
}
/**
 * 使用邻接矩阵实现图
 * @author andy
 */
class AdjacencyMatrixGraph extends Graph{

private static final int MAX_SIZE_OF_VERTEX = 100;
	
	/*顶点集合*/
	private String[] vertex = new String[MAX_SIZE_OF_VERTEX];
	
	/*各个顶点之间边的关系*/
	private int[][] edge = new int[MAX_SIZE_OF_VERTEX][MAX_SIZE_OF_VERTEX];
	
	@Override
	public Graph createGraph() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
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
		
		System.out.println("请输入每条边例如(顶点i的索引,顶点j的索引)，并按回车键\n");
		// 建立邻接矩阵
		for (int i = 0; i < graph.e; i++) {
			String str = in.next();
			String[] strs = str.split(",");
			int rowIndex = Integer.parseInt(strs[0]);
			int colIndex = Integer.parseInt(strs[1]);
			graph.edge[rowIndex][colIndex] = 1;
			graph.edge[colIndex][rowIndex] = 1;
		}
		
		in.close();
		return graph;
	}

	@Override
	public void showGraph(Graph g) {
		if(g instanceof AdjacencyMatrixGraph){
			System.out.println("图已经成功建立！");
			System.out.println("下面是该图邻接矩阵：");
			for (int i = 0; i < g.n; i++) {
				for (int k = 0; k < g.n; k++) {
					System.out.print(((AdjacencyMatrixGraph) g).edge[i][k]+" ");
				}
				System.out.println();
			}
		}
	}
	
}

/**
 * 使用邻接表实现图
 * @author andy
 */
class AdjacencyListGraph extends Graph{
	
	/**顶点表头节点**/
	private VertexNode head ;
	
	/**顶点表尾节点**/
	private VertexNode tail ;
	
	private void addToTail(String vertex){
		VertexNode newNode = new VertexNode();
		newNode.vertex = vertex;
		if(head == null && tail == null){
			head = newNode;
			tail = newNode;
		}else{
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	private VertexNode findByName(String name){
		VertexNode temp = this.head;
		while(temp != null){
			if(temp.vertex.equals(name))
				return temp;
			temp = temp.next;
		}
		return null;
		
	}
	
	@Override
	public Graph createGraph() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入图中顶点的数量:");
		this.n = in.nextInt();
		System.out.println("请输入图中边的数量:");
		this.e = in.nextInt();
		// 1.初始化顶点
		System.out.println("请依次输入每个顶点按回车结束\n");
		for (int i = 0; i < this.n; i++) {
			String verLine = in.next();
			addToTail(verLine);
		}
		
		// 2.构建边表
		System.out.println("请依次输入两个顶点并回车。例如(a,b),这代表两个顶点之间存在一条边");
		for (int i = 0; i < this.e; i++) {
			String eLine = in.next();
			String[] edgeArray = eLine.split(",");
			// 确保输入的两个顶点存在于顶点表中
			VertexNode firstNode = findByName(edgeArray[0]);
			VertexNode secondNode = findByName(edgeArray[1]);
			if(firstNode != null && secondNode != null){
				// 构造边表节点
				EdgeNode edgeNode = new EdgeNode();
				edgeNode.vertex = edgeArray[1];
				edgeNode.next = firstNode.firstEdge;
				firstNode.firstEdge = edgeNode;
				
				edgeNode = new EdgeNode();
				edgeNode.vertex = edgeArray[0];
				edgeNode.next = secondNode.firstEdge;
				secondNode.firstEdge = edgeNode;
				
			}
		}
		
		in.close();
		return this;
	}

	@Override
	public void showGraph(Graph g) {
		AdjacencyListGraph adjGraph = null;
		if(g instanceof AdjacencyListGraph){
			adjGraph = (AdjacencyListGraph) g;
		}
		System.out.println("图已经成功建立，信息如下");
		VertexNode temp = adjGraph.head;
		while(temp != null){
			System.out.printf("顶点:%s->",temp.vertex);
			EdgeNode node = temp.firstEdge;
			while(node != null){
				System.out.printf("%s->",node.vertex);
				node = node.next;
			}
			System.out.println("");
			temp = temp.next;
		}
	}
	
	/**
	 * 边表节点
	 * @author andy
	 */
	private static class EdgeNode{
		String vertex;
		EdgeNode next;
	}
	
	/**
	 * 顶点
	 * @author andy
	 */
	private static class VertexNode{
		
		/**顶点表**/
		String vertex;
		
		/**顶点指向的第一条边**/
		EdgeNode firstEdge;
		
		/**顶点所指向的下一个顶点**/
		VertexNode next;
	}
	
}
