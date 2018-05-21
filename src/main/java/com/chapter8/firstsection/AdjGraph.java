package com.chapter8.firstsection;

import java.util.Scanner;

/**
 * <p>AdjGraph</p>
 * <p>ʹ���ڽӱ��ʾͼ</p>
 * @author yanan
 * @date 2018��5��21��
 */
public class AdjGraph {

	private static final int MAX_SIZE_OF_VERTEX = 1000;
	
	/*�����*/
	VertexNode[] vexList = new VertexNode[MAX_SIZE_OF_VERTEX];
	
	/*�������*/
	private int n;
	
	/*ͼ�бߵ�����*/
	private int e;
	
	public AdjGraph createGraph(){
		AdjGraph rs = new AdjGraph();
		Scanner in = new Scanner(System.in);
		System.out.println("������ͼ�ж��������");
		// 1.����ͼ�Ķ�������ͱ���
		rs.n = in.nextInt();
		System.out.println("������ͼ�бߵ�����");
		rs.e = in.nextInt();
		
		// 2.���������
		System.out.println("�����붥�����Ʋ����س�:");
		for (int i = 0; i < rs.n; i++) {
			String v = in.next();
			rs.vexList[i] = new VertexNode();
			rs.vexList[i].vertex = v;
			rs.vexList[i].firstEdge = null;
		}
		System.out.println("�����붥�����Ʋ����س�:");
		// 3.�����������Ϣ�������߱�
		for (int i = 0; i < rs.e; i++) {
			System.out.println("����������0,3�����������ִ������Ϣ������ÿ�����ֶ��Ƕ�����е�һ��������");
			String line = in.next();
			String[] array = line.split(",");
			int tail = Integer.parseInt(array[0]);
			int head = Integer.parseInt(array[1]);
			// �����߽ڵ�
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
		System.out.println("ͼ�Ѿ��ɹ���������Ϣ����");
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
 * ͼ�ж�����
 * @author andy
 */
class VertexNode{
	String vertex;//������Ϣ
	EdgeNode firstEdge;//������ͷָ��
}

/**
 * ͼ�б���
 * @author andy
 *
 */
class EdgeNode{
	int index;	//�����±�
	int weight;	//���ϵ�Ȩֵ
	EdgeNode next;//ָ����һ���ڵ�
}
