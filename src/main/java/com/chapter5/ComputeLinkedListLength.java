package com.chapter5;


/**
 * <p>Title: ComputeLinkedListLength</p>  
 * <p>Description: 编写一个递归函数，计算并返回链表的长度。</p>  
 * @author yanan  
 * @date 2018年2月11日
 */
public class ComputeLinkedListLength {

	public static void main(String[] args) {
		LinkedList<String> linkedList=new LinkedList<String>();
		linkedList.addNode("Andy");
		linkedList.addNode("Love");
		linkedList.addNode("Lemon");
		linkedList.print();
		System.out.println(computeLinkedListLength(linkedList.head));
	}
	
	public static int computeLinkedListLength(Node node){
		if(node==null){
			return 0;
		}
		return computeLinkedListLength(node.next)+1;
	}
	
	

}
class Node{
	Object data;
	Node next;
	public Node(Object data,Node next){
		this.data=data;
		this.next=next;
	}
}

class LinkedList<AnyType>{
	public Node head;
	private Node tail;
	private int size;
	public LinkedList(){
		head=new Node(null, null);
	}
	
	public void addNode(Object value){
		Node newNode=new Node(value, null);
		if(size==0){
			head=newNode;
			tail=newNode;
		}else{
			tail.next=newNode;
			tail=newNode;
		}
		size++;
	}
	
	public int size(){
		return size;
	}
	
	public void print(){
		Node temp=head;
		for(int i=0;i<this.size();i++){
			System.out.println(temp.data);
			temp=temp.next;
		}
	}
}
