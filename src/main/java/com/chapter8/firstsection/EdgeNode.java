package com.chapter8.firstsection;
/**
 * 图中边类
 * @author andy
 *
 */
public class EdgeNode{
	int index;	//顶点下标
	int weight;	//边上的权值
	EdgeNode next;//指向下一个节点
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public EdgeNode getNext() {
		return next;
	}
	public void setNext(EdgeNode next) {
		this.next = next;
	}
	
}