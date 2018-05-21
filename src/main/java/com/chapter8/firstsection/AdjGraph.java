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
	VertexNode[] vexList = new VertexNode[MAX_SIZE_OF_VERTEX];
	
	/*顶点个数*/
	private int n;
	
	/*图中边的数量*/
	private int e;
	
	public AdjGraph createGraph(){
		AdjGraph rs = new AdjGraph();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入图中顶点的数量");
		// 1.输入图的顶点个数和边数
		rs.n = in.nextInt();
		System.out.println("请输入图中边的数量");
		rs.e = in.nextInt();
		
		// 2.建立顶点表
		System.out.println("请输入顶点名称并按回车:");
		for (int i = 0; i < rs.n; i++) {
			String v = in.next();
			rs.vexList[i] = new VertexNode();
			rs.vexList[i].vertex = v;
			rs.vexList[i].firstEdge = null;
		}
		System.out.println("请输入顶点名称并按回车:");
		// 3.逐条输入边信息，建立边表。
		for (int i = 0; i < rs.e; i++) {
			System.out.println("请依次输入0,3（这两个数字代表边信息，其中每个数字都是顶点表中的一个索引）");
			String line = in.next();
			String[] array = line.split(",");
			int tail = Integer.parseInt(array[0]);
			int head = Integer.parseInt(array[1]);
			// 建立边节点
			EdgeNode edgeNode = new EdgeNode();
			edgeNode.index = head;
			edgeNode.next = rs.vexList[tail].firstEdge;
			rs.vexList[tail].firstEdge = edgeNode;
			
			edgeNode = new EdgeNode();
			edgeNode.index = tail;
			edgeNode.next = rs.vexList[head].firstEdge;
			rs.vexList[head].firstEdge = edgeNode;
			
		}
		in.close();
		System.out.println("图已经成功建立，信息如下");
		for (int i = 0; i < rs.n; i++) {
			System.out.printf("%d:%s->",i,rs.vexList[i].vertex);
			EdgeNode node = rs.vexList[i].firstEdge;
			while(node != null){
				System.out.printf("%s->",rs.vexList[node.index].vertex);
				node = node.next;
			}
			System.out.println("");
		}
		return rs;
	}
	
	public static void main(String[] args) {
		AdjGraph graph = new AdjGraph();
		graph.createGraph();
	}

}
/**
 * 图中顶点类
 * @author andy
 */
class VertexNode{
	String vertex;//顶点信息
	EdgeNode firstEdge;//边链表头指针
}

/**
 * 图中边类
 * @author andy
 *
 */
class EdgeNode{
	int index;	//顶点下标
	int weight;	//边上的权值
	EdgeNode next;//指向下一个节点
}
