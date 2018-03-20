package com.chapter3.text;


/**
 * <p>IntSingleCircularLinkedList</p>  
 * <p>保存整数的单向循环链表 </p>  
 * @author yanan  
 * @date 2018年3月20日
 * 有价值的资源:https://www.sanfoundry.com/java-program-implement-circular-singly-linked-list/
 */
public class IntSingleCircularLinkedList {

	private Node head,tail;
	private int size;
	public IntSingleCircularLinkedList(){
		head=tail=new Node(null, null);
	}
	
	public void clear(){
		head=tail=new Node(null, null);
		size=0;
	}
	
	/**
	 * 将数据添加到链表头部
	 * @param data 待添加的数据
	 */
	public void addToHead(Integer data){
		Node newNode=new Node(data, head);
		if(isEmpty()){
			head=tail=newNode;
		}else{
			tail.setNext(newNode);
			head=newNode;
		}
		size++;
	}
	
	/**
	 * 将数据添加到链表尾部
	 * @param data 待添加的数据
	 */
	public void addToTail(Integer data){
		Node newNode=new Node(data, null);
		if(isEmpty()){
			head=tail=new Node(data, null);
		}else{
			tail.setNext(newNode);
			newNode.setNext(head);
			tail=newNode;
		}
		size++;
	}
	
	/**
	 * 从指定位置删除链表中的数据
	 * @param index 数据位于链表中的索引
	 */
	public void deleteNode(int index){
		if(index>=size){
			System.out.println("...................");
		}
		rangeCheck(index);
		if(index==0){
			deleteFromHead();
		}else if(index==size()-1){
			deleteFromTail();
		}else{
			int i=0;
			Node tmpNode=head;
			Node prevNode=null;
			while(i<index){
				prevNode=tmpNode;
				tmpNode=tmpNode.getNext();
				i++;
			}
			prevNode.setNext(tmpNode.getNext());
			size--;
		}
	}
	
	/**
	 * 从链表头部删除数据并返回改数据，如果链表为空，则返回null。
	 */
	public Integer deleteFromHead(){
		if(!isEmpty()){
			Integer rs=head.getInfo();
			if(size==1){
				head=tail=new Node(null, null);
			}else{
				tail.setNext(head.getNext());
				head=head.getNext();
			}
			size--;
			return rs;
		}
		return null;
	}
	
	/**
	 * 从链表尾部删除数据并返回改数据，如果链表为空，则返回null。
	 */
	public Integer deleteFromTail(){
		if(!isEmpty()){
			Integer rs=tail.getInfo();
			if(size()==1){
				clear();
			}else{
				int index=1;
				Node temp=head;
				while(index<size()-1){
					temp=temp.getNext();
					index++;
				}
				temp.setNext(head);
				tail=temp;
				size--;
			}
			return rs;
		}
		return null;
	}
	
	/**
	 * 获取链表指定位置的数据
	 * @param i 索引
	 * @return int 数据
	 */
	public Integer get(int i){
		rangeCheck(i);
		if(i==0){
			return head.getInfo();
		}
		if(i==size-1){
			return tail.getInfo();
		}
		int tmpIndex=0;
		Node tmpNode=head; 
		while(tmpIndex!=i){
			tmpNode=tmpNode.getNext();
			tmpIndex++;
		}
		return tmpNode.getInfo();
	}
	
	/**
	 * 为解决约瑟夫环问题....
	 * @param i 索引
	 * @return Node 节点
	 */
	public Node getNode(int i){
		rangeCheck(i);
		if(i==0){
			return head;
		}
		if(i==size-1){
			return tail;
		}
		int tmpIndex=0;
		Node tmpNode=head; 
		while(tmpIndex!=i){
			tmpNode=tmpNode.getNext();
			tmpIndex++;
		}
		return tmpNode;
	}
	
	private void rangeCheck(int i) {
		if(i<0 || i>=size){
			throw new ArrayIndexOutOfBoundsException(i);
		}
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0?true:false;
	}
	
}
