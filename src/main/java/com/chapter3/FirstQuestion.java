package com.chapter3;


public class FirstQuestion {

	public static void main(String[] args) {
		Node list=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		Node node5=new Node(5);
		list.next=node2;
		list.prev=node5;
		node2.next=node3;
		node2.prev=list;
		node3.next=node4;
		node3.prev=node2;
		node4.next=node5;
		node4.prev=node3;
		node5.next=list;
		node5.prev=node4;
		list.next.next.next=list.prev;
		list.prev.prev.prev=list.next.next.next.prev;
		list.next.next.next.prev=list.prev.prev.prev;
		list.next=list.next.next;
		list.next.prev.next=list.next.next.next;
		System.out.println(list);
		
				
	}

}
class Node{
	int info;
	Node prev,next;
	public Node(int info, Node prev, Node next) {
		super();
		this.info = info;
		this.prev = prev;
		this.next = next;
	}
	public Node(int info) {
		super();
		this.info = info;
	}
}
