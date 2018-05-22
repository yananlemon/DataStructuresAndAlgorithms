package com.chapter8.practice.liaominghong;

import java.util.Scanner;

/**
 * <p>ThirdQuestion</p>
 * <p>ϰ�������</p>
 * @author yanan
 * @date 2018��5��22�� 
 */
public class ThirdQuestion {

	public static void main(String[] args) {
		// ʹ���ڽӱ���ͼ
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
		
		// ʹ���ڽӾ��󴴽�ͼ
		/*Graph adjList = new AdjacencyMatrixGraph();
		Graph created = adjList.createGraph();//E:{(0,1),(0,3),(1,2),(1,4),(2,3),(2,4),(3,4)}
		adjList.showGraph(created);*/
	}
	

}

abstract class Graph{
	protected int n;//��������
	protected int e;//����
	public abstract Graph createGraph();
	public abstract void showGraph(Graph g);
}
/**
 * ʹ���ڽӾ���ʵ��ͼ
 * @author andy
 */
class AdjacencyMatrixGraph extends Graph{

private static final int MAX_SIZE_OF_VERTEX = 100;
	
	/*���㼯��*/
	private String[] vertex = new String[MAX_SIZE_OF_VERTEX];
	
	/*��������֮��ߵĹ�ϵ*/
	private int[][] edge = new int[MAX_SIZE_OF_VERTEX][MAX_SIZE_OF_VERTEX];
	
	@Override
	public Graph createGraph() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
		Scanner in = new Scanner(System.in);
		System.out.println("������ͼ�Ķ�������\n");
		graph.n = in.nextInt();
		System.out.println("������ͼ�ı���\n");
		graph.e = in.nextInt();
		System.out.println("��ÿ������һ���������ƣ������س���\n");
		
		// ���������
		for (int i = 0; i < graph.n; i++) {
			graph.vertex[i] = in.next();
		}
		
		// ��ʼ���ڽӾ���
		for (int i = 0; i < graph.n; i++) {
			for (int j = 0; j < graph.n; j++) {
				graph.edge[i][j] = 0;
			}
		}
		
		System.out.println("������ÿ��������(����i������,����j������)�������س���\n");
		// �����ڽӾ���
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
			System.out.println("ͼ�Ѿ��ɹ�������");
			System.out.println("�����Ǹ�ͼ�ڽӾ���");
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
 * ʹ���ڽӱ�ʵ��ͼ
 * @author andy
 */
class AdjacencyListGraph extends Graph{
	
	/**�����ͷ�ڵ�**/
	private VertexNode head ;
	
	/**�����β�ڵ�**/
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
		System.out.println("������ͼ�ж��������:");
		this.n = in.nextInt();
		System.out.println("������ͼ�бߵ�����:");
		this.e = in.nextInt();
		// 1.��ʼ������
		System.out.println("����������ÿ�����㰴�س�����\n");
		for (int i = 0; i < this.n; i++) {
			String verLine = in.next();
			addToTail(verLine);
		}
		
		// 2.�����߱�
		System.out.println("�����������������㲢�س�������(a,b),�������������֮�����һ����");
		for (int i = 0; i < this.e; i++) {
			String eLine = in.next();
			String[] edgeArray = eLine.split(",");
			// ȷ�������������������ڶ������
			VertexNode firstNode = findByName(edgeArray[0]);
			VertexNode secondNode = findByName(edgeArray[1]);
			if(firstNode != null && secondNode != null){
				// ����߱�ڵ�
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
		System.out.println("ͼ�Ѿ��ɹ���������Ϣ����");
		VertexNode temp = adjGraph.head;
		while(temp != null){
			System.out.printf("����:%s->",temp.vertex);
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
	 * �߱�ڵ�
	 * @author andy
	 */
	private static class EdgeNode{
		String vertex;
		EdgeNode next;
	}
	
	/**
	 * ����
	 * @author andy
	 */
	private static class VertexNode{
		
		/**�����**/
		String vertex;
		
		/**����ָ��ĵ�һ����**/
		EdgeNode firstEdge;
		
		/**������ָ�����һ������**/
		VertexNode next;
	}
	
}
