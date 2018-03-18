package com.chapter3;

import com.chapter3.text.Node;
import com.chapter3.text.SingleLinkedList;

/**
 * <p>反转单链表</p>  
 * <p>参考https://stackoverflow.com/questions/14080758/reversing-a-linkedlist-recursively-in-c</p>  
 * @author yanan  
 * @date 2018年3月16日
 */
public class ReverseSingleLinkedList {

	public static void main(String[] args) {
		SingleLinkedList list=new SingleLinkedList();
		for(int i=1;i<=5;i++){
			list.addToTail(i);
		}
		//Node reversed=reverseLinkedList(list.getHead());
		Node reversed=reverseLinkedListWithRecursion(list.getHead());
		Node temp=reversed;
		while(temp!=null){
			System.out.println(temp.getInfo());
			temp=temp.getNext();
		}
	}
	
	/**
	 * 反转单链表使用迭代
	 * @param head
	 * @return
	 */
	static Node reverseLinkedListWithIterator(Node head){
		if(head==null || head.getNext()==null){
			return head;
		}
		//1->2->3->null
		Node currNode=head;//1
		Node prevNode=null;//null
		Node nextNode=null;//null
		while(currNode!=null){
			nextNode=currNode.getNext();//2
			currNode.setNext(prevNode);//1->null
			prevNode=currNode;//1
			currNode=nextNode;//2
		}
		return prevNode;
	}
	
	/**
	 * 反转单链表使用递归
	 * @param head
	 * @return
	 */
	static Node reverseLinkedListWithRecursion(Node head){
		if(head==null){
			return null;
		}
		if(head.getNext()==null){
			return head;
		}
		Node last=reverseLinkedListWithRecursion(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return last;
	}

}
