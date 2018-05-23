package com.chapter8.firstsection;
/**
 * 图中顶点类
 * @author andy
 */
public class VertexNode{
	String vertex;//顶点信息
	EdgeNode firstEdge;//边链表头指针
	public String getVertex() {
		return vertex;
	}
	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	public EdgeNode getFirstEdge() {
		return firstEdge;
	}
	public void setFirstEdge(EdgeNode firstEdge) {
		this.firstEdge = firstEdge;
	}
	
}