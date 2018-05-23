package com.chapter8.firstsection;

import java.util.Scanner;

/**
 * <p>AdjGraph</p>
 * <p>使用邻接表表示图</p>
 * @author yanan
 * @date 2018年5月21日
 */
public class AdjGraph {

	private static final int MAX_SIZE_OF_VERTEX = 1000;
	
	/*顶点表*/
	private VertexNode[] vexList = new VertexNode[MAX_SIZE_OF_VERTEX];
	
	/*顶点个数*/
	private int n;
	
	/*图中边的数量*/
	private int e;
	
	public AdjGraph createGraph(){
		Scanner in = new Scanner(System.in);
		System.out.println("请输入图中顶点的数量");
		// 1.输入图的顶点个数和边数
		this.n = in.nextInt();
		System.out.println("请输入图中边的数量");
		this.e = in.nextInt();
		
		// 2.建立顶点表
		System.out.println("请输入顶点名称并按回车:");
		for (int i = 0; i < this.n; i++) {
			String v = in.next();
			this.vexList[i] = new VertexNode();
			this.vexList[i].vertex = v;
			this.vexList[i].firstEdge = null;
		}
		System.out.println("请输入顶点名称并按回车:");
		// 3.逐条输入边信息，建立边表。
		System.out.println("请依次输入0,3（这两个数字代表边信息，其中每个数字都是顶点表中的一个索引）");
		for (int i = 0; i < this.e; i++) {
			String line = in.next();
			String[] array = line.split(",");
			int tail = Integer.parseInt(array[0]);
			int head = Integer.parseInt(array[1]);
			// 建立边节点
			EdgeNode edgeNode = new EdgeNode();
			edgeNode.index = head;
			edgeNode.next = this.vexList[tail].firstEdge;
			this.vexList[tail].firstEdge = edgeNode;
			
			edgeNode = new EdgeNode();
			edgeNode.index = tail;
			edgeNode.next = this.vexList[head].firstEdge;
			this.vexList[head].firstEdge = edgeNode;
			
		}
		in.close();
		System.out.println("图已经成功建立，信息如下");
		for (int i = 0; i < this.n; i++) {
			System.out.printf("%d:%s->",i,this.vexList[i].vertex);
			EdgeNode node = this.vexList[i].firstEdge;
			while(node != null){
				System.out.printf("%s->",this.vexList[node.index].vertex);
				node = node.next;
			}
			System.out.println("");
		}
		return this;
	}
	
	public static void main(String[] args) {
		AdjGraph graph = new AdjGraph();
		graph.createGraph();
	}

	public VertexNode[] getVexList() {
		return vexList;
	}

	public void setVexList(VertexNode[] vexList) {
		this.vexList = vexList;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}
	
	

}
