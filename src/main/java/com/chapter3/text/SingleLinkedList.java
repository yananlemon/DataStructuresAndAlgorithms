package com.chapter3.text;

public class SingleLinkedList {
	private Node head,tail;
	private int size;
	public SingleLinkedList(){
		head=tail=new Node(null, null);
	}
	
	public void addToHead(Integer data){
		if(isEmpty()){
			head=tail=new Node(data, null);
		}else{
			Node newNode=new Node(data, null);
			newNode.next=head;;
			head=newNode;
		}
		size++;
	}
	
	public void addToTail(Integer data){
		if(isEmpty()){
			head=tail=new Node(data, null);
		}else{
			Node newNode=new Node(data, null);
			tail.next=newNode;
			tail=tail.next;
		}
		size++;
	}
	
	public Integer deleteFromHead(){
		if(!isEmpty()){
			Integer rs=head.info;
			if(size==1){
				head=tail=new Node(null, null);
			}else{
				head=head.next;
			}
			size--;
			return rs;
		}
		return null;
	}
	
	public Integer deleteFromTail(){
		if(!isEmpty()){
			Integer rs=null;
			if(size==1){
				rs=head.info;
				head=tail=new Node(null, null);
				return rs;
			}else{
				Node tmp=head;
				Node prev=null;
				while(tmp!=null){
					prev=tmp;
					tmp=tmp.next;
				}
				rs=tail.info;
				tail=prev;
			}
			size--;
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0?true:false;
	}
	
	public Integer get(int i){
		if(i<0 || i>=size){
			throw new ArrayIndexOutOfBoundsException(i);
		}
		if(i==0){
			return head.info;
		}
		if(i==size-1){
			return tail.info;
		}
		int tmpIndex=0;
		Node tmpNode=head; 
		while(tmpIndex!=i){
			tmpNode=tmpNode.next;
			tmpIndex++;
		}
		return tmpNode.info;
	}
}

class Node{
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
