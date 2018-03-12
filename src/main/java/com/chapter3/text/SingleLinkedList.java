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
			}else{
				Node tmp=head;
				Node prev=head;
				while(tmp.next!=null){
					prev=tmp;
					tmp=tmp.next;
				}
				rs=tail.info;
				prev.next=tmp.next;
				tail=prev;
			}
			size--;
			return rs;
		}
		return null;
	}
	
	public void deleteNode(int index){
		rangeCheck(index);
		if(size==1){
			head=tail=new Node(null, null);
			size--;
		}else if(size==0){
			head=head.next;
			size--;
		}else if(index==size-1){
			deleteFromTail();
		}else{
			int tmpIndex=0;
			Node tmpNode=head;
			Node prevNode=head,nextNode=head;
			while(tmpIndex!=index){
				prevNode=tmpNode;
				tmpNode=tmpNode.next;
				nextNode=tmpNode;
				tmpIndex++;
			}
			prevNode.next=nextNode.next;
			size--;
		}
		
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0?true:false;
	}
	
	public Integer get(int i){
		rangeCheck(i);
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

	private void rangeCheck(int i) {
		if(i<0 || i>=size){
			throw new ArrayIndexOutOfBoundsException(i);
		}
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
