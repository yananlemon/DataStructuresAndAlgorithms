package com.chapter8.thirdsection;

import java.util.Scanner;


public class AdjGraph extends AbstractGraph {

	public static void main(String[] args) {
		/*
		 E:
		 	V0,V1
		  	V0,V3
			V1,V2
			V1,V4
			V2,V3
			V2,V4
		 */
		AdjGraph g = new AdjGraph();
		g.createGraph();
		g.showGraph();
		g.dfs(g);
	}
	
	@Override
	public AbstractGraph createGraph() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入图中顶点的数量:");
		this.vertexCount = in.nextInt();
		System.out.println("请输入图中边的数量:");
		this.edgeCount = in.nextInt();
		// 1.初始化顶点
		System.out.println("请依次输入每个顶点按回车结束\n");
		for (int i = 0; i < this.vertexCount; i++) {
			String verLine = in.next();
			addToTail(verLine);
		}
		
		// 2.构建边表
		System.out.println("请依次输入两个顶点并回车。例如(a,b),这代表两个顶点之间存在一条边");
		for (int i = 0; i < this.edgeCount; i++) {
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
	public void dfs(AbstractGraph graph) {
		
		/*// 选取图顶点表中第一个节点开始遍历
		VertexNode temp = this.head;
		// 扫描图中每个顶点
		while(temp != null){
			if(!temp.visited)
				dfs1(graph, temp);
			temp = temp.next;
		}*/
	}
	
	private void dfs1(AbstractGraph graph, VertexNode node) {
		
		// 访问顶点
		/*System.out.println(node.vertex);
		node.visited = true;
		EdgeNode firstEdge = node.firstEdge;
		VertexNode temp = findByName(firstEdge.vertex);
		while(temp != null){
			if(!temp.visited)
				dfs1(graph, temp);
			temp = temp.next;
		}*/
	}

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

	public void showGraph() {
		System.out.println("图信息如下");
		VertexNode temp = this.head;
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
	
}

/**
 * 边表节点
 * @author andy
 */
class EdgeNode{
	String vertex;
	EdgeNode next;
}

/**
 * 顶点
 * @author andy
 */
class VertexNode{
	
	/**顶点表**/
	String vertex;
	
	/**顶点指向的第一条边**/
	EdgeNode firstEdge;
	
	/**顶点所指向的下一个顶点**/
	VertexNode next;
}
