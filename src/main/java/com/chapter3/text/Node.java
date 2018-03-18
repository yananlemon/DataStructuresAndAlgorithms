package com.chapter3.text;

public class Node{
	Integer info;
	Node next;
	public Node(Integer info, Node next) {
		super();
		this.info = info;
		this.next = next;
	}
	public Integer getInfo() {
		return info;
	}
	public void setInfo(Integer info) {
		this.info = info;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}