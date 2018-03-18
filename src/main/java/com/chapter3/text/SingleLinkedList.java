package com.chapter3.text;

/**
 * <p>SingleLinkedList</p>  
 * <p>保存整数的单链表 </p>  
 * @author yanan  
 * @date 2018年3月13日
 */
public class SingleLinkedList {
	private Node head,tail;
	private int size;
	public SingleLinkedList(){
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
		if(isEmpty()){
			head=tail=new Node(data, null);
		}else{
			Node newNode=new Node(data, null);
			newNode.next=head;;
			head=newNode;
		}
		size++;
	}
	
	/**
	 * 将数据添加到链表尾部
	 * @param data 待添加的数据
	 */
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
	
	/**
	 * 从链表头部删除数据并返回改数据，如果链表为空，则返回null。
	 */
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
	
	/**
	 * 从链表尾部删除数据并返回改数据，如果链表为空，则返回null。
	 */
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
	
	/**
	 * 从指定位置删除链表中的数据
	 * @param index 数据位于链表中的索引
	 */
	public void deleteNode(int index){
		rangeCheck(index);
		if(index==0){
			deleteFromHead();
		}else if(index==size-1){
			deleteFromTail();
		}else{
			int i=0;
			Node tmpNode=head;
			Node prevNode=null;
			while(i<index){
				prevNode=tmpNode;
				tmpNode=tmpNode.next;
				i++;
			}
			prevNode.next=tmpNode.next;
			size--;
		}
		
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0?true:false;
	}
	
	/**
	 * 获取链表指定位置的数据
	 * @param i 索引
	 * @return int 数据
	 */
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

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	
	
}


